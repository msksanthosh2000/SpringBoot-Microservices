package com.Microservice.organization_service.service.impl;

import com.Microservice.organization_service.dto.OrganizationDto;
import com.Microservice.organization_service.entity.Organization;
import com.Microservice.organization_service.mapper.OrganizationMapper;
import com.Microservice.organization_service.repository.OrganizationRepository;
import com.Microservice.organization_service.service.OrganizationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationImpl implements OrganizationInterface {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = repository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {

        Organization organizationOptional = repository.findByOrganizationCode(organizationCode);

        return OrganizationMapper.mapToOrganizationDto(organizationOptional);
    }
}
