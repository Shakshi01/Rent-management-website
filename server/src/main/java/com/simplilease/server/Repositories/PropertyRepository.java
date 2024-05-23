package com.simplilease.server.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilease.server.Entities.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    public List<Property> findByOwnerId(long ownerId);

    public Optional<Property> findByOwnerIdAndPid(long ownerId, long pId);
    
    public Optional<Property> findByOwnerIdAndPname(long ownerId, String pName);

}
