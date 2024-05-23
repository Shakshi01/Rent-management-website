package com.simplilease.server.Controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.simplilease.server.Entities.RentPayment;
import com.simplilease.server.Services.RentPaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/server/rentPaymentController")
public class RentPaymentController {
    
    @Autowired
    private RentPaymentService rpService;

    
    /**
     * Persist a new rent payment
     * @param rp
     * @return
     * @throws SQLException
     */
    @PostMapping(
        path = "/createRentPayment",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RentPayment> createRentPayment(@RequestBody RentPayment rp) throws SQLException {
        
        RentPayment newRp = rpService.createRentPayment(rp);
        return new ResponseEntity<RentPayment>(newRp, HttpStatus.OK);

    }


    /**
     * Fetch persisted rent payments for a tenant
     * @param tenantId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "fetchRentPaymentsByTenant",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RentPayment>> fetchRentsPaymentByTenant(@RequestParam long tenantId) throws SQLException {
        List<RentPayment> rpList = rpService.fetchRentPaymentsForTenant(tenantId);
        return new ResponseEntity<List<RentPayment>>(rpList, HttpStatus.OK);
    }


    /**
     * Fetch persisted rent payments for a tenant between dates
     * @param tenantId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchRentPaymentsForTenantByDateRange",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RentPayment>> fetchRentPaymentForTenantByDateRange(
        @RequestParam long tenantId,
        @RequestParam String startDate,
        @RequestParam String endDate
    ) throws SQLException {

        // convert dates to java.sql.date type
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);

        List<RentPayment> rpList = rpService.fetchRentPaymentForTenantByDateRange(tenantId, sqlStartDate, sqlEndDate);
        return new ResponseEntity<List<RentPayment>>(rpList, HttpStatus.OK);

    }


    /**
     * Fetch persisted rent payments for a property
     * @param propertyId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchRentPaymentsForProperty",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RentPayment>> fetchRentPaymentsForProperty(
        @RequestParam long propertyId
    ) throws SQLException {

        List<RentPayment> rpList = rpService.fetchRentPaymentForProperty(propertyId);
        return new ResponseEntity<List<RentPayment>>(rpList, HttpStatus.OK);
    }


    /**
     * Fetch persisted rent payments for a property between dates
     * @param propertyId
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchRentPaymentsForPropertyByDateRange",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RentPayment>> fetchRentPaymentsForPropertyByDateRange(
        @RequestParam long propertyId,
        @RequestParam String startDate,
        @RequestParam String endDate
    ) throws SQLException {

        // convert dates to java.sql.date type
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);

        List<RentPayment> rpList = rpService.fetchRentPaymentForPropertyByDateRange(propertyId, sqlStartDate, sqlEndDate);
        return new ResponseEntity<List<RentPayment>>(rpList, HttpStatus.OK);
    }


    /**
     * Fetch persisted rent payment by its ID
     * @param rpId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchRentPaymentById",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RentPayment> fetchRentPaymentById(@RequestParam long rpId) throws SQLException {

        RentPayment rp = rpService.fetchRentPaymentById(rpId).get();
        return new ResponseEntity<RentPayment>(rp, HttpStatus.OK);

    }


    /**
     * Delete a rent payment by its ID
     * @param rpId
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/deleteRentPaymentById")
    public ResponseEntity<?> deleteRentPaymentById(@RequestParam long rpId) throws SQLException {
        rpService.deleteRentPaymentById(rpId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
