package com.Microservice.organization_service.mapper;

import com.Microservice.organization_service.dto.OrganizationDto;
import com.Microservice.organization_service.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        return new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        return new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreatedDate()
        );
    }
}
