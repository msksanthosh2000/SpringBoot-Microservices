package com.Microservice.organization_service.repository;

import com.Microservice.organization_service.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

   Organization findByOrganizationCode(String organizationCode);
}
