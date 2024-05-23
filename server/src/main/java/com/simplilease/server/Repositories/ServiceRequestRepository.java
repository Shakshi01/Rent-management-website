package com.simplilease.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilease.server.Entities.ServiceRequest;
import java.util.List;


public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    
    List<ServiceRequest> findByPid(long propertyId);

    List<ServiceRequest> findByPidAndStatus(long propertyId, String status);

    List<ServiceRequest> findByPidAndType(long propertyId, String type);

}
