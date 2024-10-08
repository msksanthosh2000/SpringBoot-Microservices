package com.Microservice.organization_service.service;


import com.Microservice.organization_service.dto.OrganizationDto;
import org.springframework.stereotype.Service;


public interface OrganizationInterface {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String code);

}
