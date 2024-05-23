package com.simplilease.server.Services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilease.server.Entities.RentPayment;
import com.simplilease.server.Repositories.RentPaymentRepository;

@Service
public class RentPaymentService {
    
    @Autowired
    private RentPaymentRepository rpRepo;

    /**
     * Create and persist rent payment
     * @param rp
     * @return
     * @throws SQLException
     */
    public RentPayment createRentPayment(RentPayment rp) throws SQLException {
        RentPayment newRp = rpRepo.save(rp);
        return newRp;
    }


    /**
     * Fetch all persisted rent payments for a tenant
     * @param tenantId
     * @return
     * @throws SQLException
     */
    public List<RentPayment> fetchRentPaymentsForTenant(long tenantId) throws SQLException {
        List<RentPayment> rpList = rpRepo.findByTenantId(tenantId);
        return rpList;
    }


    /**
     * Fetch all persisted rent payments for a tenant by date range
     * @param tenantId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    public List<RentPayment> fetchRentPaymentForTenantByDateRange(
        long tenantId,
        Date startDate,
        Date endDate
    ) throws SQLException {
        List<RentPayment> rpList = rpRepo.findByTenantIdAndDateBetween(tenantId, startDate, endDate);
        return rpList;
    }


    /**
     * Fetch persisted rent payments for a property
     * @param propertyId
     * @return
     * @throws SQLException
     */
    public List<RentPayment> fetchRentPaymentForProperty(long propertyId) throws SQLException {
        List<RentPayment> rpList = rpRepo.findByPropertyId(propertyId);
        return rpList;
    }


    /**
     * Fetch persisted rent payments for a property between dates
     * @param propertyId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    public List<RentPayment> fetchRentPaymentForPropertyByDateRange(
        long propertyId,
        Date startDate,
        Date endDate
    ) throws SQLException {
        List<RentPayment> rpList = rpRepo.findByPropertyIdAndDateBetween(propertyId, startDate, endDate);
        return rpList;
    }


    /**
     * Fetch persisted rent payment by its ID
     * @param rpId
     * @return
     * @throws SQLException
     */
    public Optional<RentPayment> fetchRentPaymentById(long rpId) throws SQLException {
        Optional<RentPayment> oRp = rpRepo.findById(rpId);
        return oRp;
    }


    /**
     * Delete persisted rent payment by its ID
     * @param rpId
     * @throws SQLException
     */
    public void deleteRentPaymentById(long rpId) throws SQLException {
        rpRepo.deleteById(rpId);
    }


}
