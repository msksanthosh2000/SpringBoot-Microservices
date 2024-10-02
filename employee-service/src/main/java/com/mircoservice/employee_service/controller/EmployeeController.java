package com.mircoservice.employee_service.controller;

import com.mircoservice.employee_service.dto.ApiResponse;
import com.mircoservice.employee_service.dto.EmployeeDto;
import com.mircoservice.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable long id){
        ApiResponse apiResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(apiResponse);
    }
}
