package com.mircoservice.employee_service.service.impl;

import com.mircoservice.employee_service.Converter;
import com.mircoservice.employee_service.dto.ApiResponse;
import com.mircoservice.employee_service.dto.DepartmentDto;
import com.mircoservice.employee_service.dto.EmployeeDto;
import com.mircoservice.employee_service.dto.OrganizationDto;
import com.mircoservice.employee_service.entity.Employee;
import com.mircoservice.employee_service.repository.EmployeeRepository;
import com.mircoservice.employee_service.service.ApiClient;
import com.mircoservice.employee_service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    private EmployeeRepository repository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

//    private ApiClient apiClient;

//    @Autowired
//    public EmployeeServiceImpl(ApiClient apiClient) {
//        this.apiClient = apiClient;
//    }
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = Converter.convertToEmployee(employeeDto);
        Employee savedEmployee = repository.save(employee);

        return Converter.convertToEmployeeDto(savedEmployee);
    }

    @Override
//    @CircuitBreaker(name= "${spring.application.name}" , fallbackMethod = "getDefaultDepartment")
    @Retry(name ="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public ApiResponse getEmployeeById(long id) {

        log.info("Inside getEmployeeById");
        Employee employee = repository.findById(id).get();

        // Call Department by RestTemplate
//        ResponseEntity<DepartmentDto> dtoResponseEntity = restTemplate.getForEntity
//                ("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);

//        DepartmentDto departmentDto = dtoResponseEntity.getBody();

//         Call Department by Webclient
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();



        // call Department by ApiClient
//        DepartmentDto departmentDto;
//        try{
//            departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
//        }
//        catch (Exception e){
//            log.warn(String.valueOf(e));
//            throw e;
//        }

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();




        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setEmployeeDto(Converter.convertToEmployeeDto(employee));
        apiResponse.setDepartmentDto(departmentDto);
        apiResponse.setOrganizationDto(organizationDto);
        return apiResponse;
    }

    public ApiResponse getDefaultDepartment(long id, Exception e) {

        log.info("Inside getDefaultDepartment");
        Employee employee = repository.findById(id).get();

        // Default Department
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD01");
        departmentDto.setDepartmentDescription("Research and Development Department");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setEmployeeDto(Converter.convertToEmployeeDto(employee));
        apiResponse.setDepartmentDto(departmentDto);
        return apiResponse;
    }
}
