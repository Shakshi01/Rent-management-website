package com.simplilease.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilease.server.Entities.Tenant;

import java.util.Optional;
import java.util.List;


public interface TenantRepository extends JpaRepository<Tenant, Long>{
    
    public Optional<Tenant> findByUname(String uname);

    public Optional<Tenant> findByEmail(String email);

    public Optional<Tenant> findByPnumber(String pnumber);

    public List<Tenant> findByPropertyId(long propertyId);

}
