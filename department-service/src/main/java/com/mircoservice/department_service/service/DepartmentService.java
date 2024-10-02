package com.mircoservice.department_service.service;

import com.mircoservice.department_service.dto.DepartmentDto;
import org.springframework.stereotype.Service;


public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto dto);

    DepartmentDto getDepartmentByCode(String code);
}
