package com.mircoservice.employee_service.service;


import com.mircoservice.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign library will dynamically create the implementation
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {

    @GetMapping("api/departments/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable String code);
}
