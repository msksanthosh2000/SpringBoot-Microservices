package com.mircoservice.department_service.repository;

import com.mircoservice.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentCode(String code);
}
