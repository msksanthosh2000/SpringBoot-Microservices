package com.mircoservice.department_service.service.impl;

import com.mircoservice.department_service.Converter;
import com.mircoservice.department_service.dto.DepartmentDto;
import com.mircoservice.department_service.entity.Department;
import com.mircoservice.department_service.repository.DepartmentRepository;
import com.mircoservice.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = Converter.convertToDepartment(departmentDto);

        Department savedDepartment = repository.save(department);

        return Converter.convertToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        Department department = repository.findByDepartmentCode(code);

        return Converter.convertToDepartmentDto(department);
    }
}
