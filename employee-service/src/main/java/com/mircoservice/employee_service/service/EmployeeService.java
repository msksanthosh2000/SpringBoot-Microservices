package com.mircoservice.employee_service.service;

import com.mircoservice.employee_service.dto.ApiResponse;
import com.mircoservice.employee_service.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    ApiResponse getEmployeeById(long id);
}
