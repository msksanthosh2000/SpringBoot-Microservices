package com.mircoservice.department_service;

import com.mircoservice.department_service.dto.DepartmentDto;
import com.mircoservice.department_service.entity.Department;

public class  Converter {

    public static Department convertToDepartment(DepartmentDto departmentDto) {
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
    }

    public static DepartmentDto convertToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }

}
