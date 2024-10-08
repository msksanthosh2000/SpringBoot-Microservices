package com.Microservice.organization_service.controller;

import com.Microservice.organization_service.dto.OrganizationDto;
import com.Microservice.organization_service.service.impl.OrganizationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationImpl organization;

    @PostMapping
    public OrganizationDto saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return organization.saveOrganization(organizationDto);
    }

    @GetMapping("/{code}")
    public OrganizationDto getOrganizationByCode(@PathVariable String code) {
        return organization.getOrganizationByCode(code);
    }
}
