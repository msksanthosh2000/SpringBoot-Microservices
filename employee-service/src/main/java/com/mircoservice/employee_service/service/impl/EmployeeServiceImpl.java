package com.mircoservice.employee_service.service.impl;

import com.mircoservice.employee_service.Converter;
import com.mircoservice.employee_service.dto.ApiResponse;
import com.mircoservice.employee_service.dto.DepartmentDto;
import com.mircoservice.employee_service.dto.EmployeeDto;
import com.mircoservice.employee_service.entity.Employee;
import com.mircoservice.employee_service.repository.EmployeeRepository;
import com.mircoservice.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = Converter.convertToEmployee(employeeDto);
        Employee savedEmployee = repository.save(employee);

        return Converter.convertToEmployeeDto(savedEmployee);
    }

    @Override
    public ApiResponse getEmployeeById(long id) {

        Employee employee = repository.findById(id).get();

        // Call Department by RestTemplate
//        ResponseEntity<DepartmentDto> dtoResponseEntity = restTemplate.getForEntity
//                ("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);

//        DepartmentDto departmentDto = dtoResponseEntity.getBody();

        // Call Department by Webclient
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();



        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setEmployeeDto(Converter.convertToEmployeeDto(employee));
        apiResponse.setDepartmentDto(departmentDto);
        return apiResponse;
    }
}
