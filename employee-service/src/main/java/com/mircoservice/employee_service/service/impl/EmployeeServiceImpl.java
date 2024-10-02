package com.mircoservice.employee_service.service.impl;

import com.mircoservice.employee_service.Converter;
import com.mircoservice.employee_service.dto.EmployeeDto;
import com.mircoservice.employee_service.entity.Employee;
import com.mircoservice.employee_service.repository.EmployeeRepository;
import com.mircoservice.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = Converter.convertToEmployee(employeeDto);
        Employee savedEmployee = repository.save(employee);

        return Converter.convertToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {

        Employee employee = repository.findById(id).get();

        return Converter.convertToEmployeeDto(employee);
    }
}
