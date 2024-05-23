package com.simplilease.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilease.server.Entities.RentPayment;

import java.sql.Date;
import java.util.List;


public interface RentPaymentRepository extends JpaRepository<RentPayment, Long> {
    
    List<RentPayment> findByTenantId(long tenantId);

    List<RentPayment> findByTenantIdAndDateBetween(long tenantId, Date startDate, Date endDate);

    List<RentPayment> findByPropertyId(long propertyId);

    List<RentPayment> findByPropertyIdAndDateBetween(long propertyId, Date startDate, Date endDate);

}
