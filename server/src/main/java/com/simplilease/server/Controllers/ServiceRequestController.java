package com.simplilease.server.Controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilease.server.Entities.ServiceRequest;
import com.simplilease.server.Services.ServiceRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/server/serviceRequestController")
public class ServiceRequestController {
    
    @Autowired
    private ServiceRequestService srService;


    /**
     * Persist a new service request
     * @param sr
     * @return
     * @throws SQLException
     */
    @PostMapping(
        path = "/createServiceRequest",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ServiceRequest> createServiceRequest(
        @RequestBody ServiceRequest sr
    ) throws SQLException {

        ServiceRequest newSr = srService.createServiceRequest(sr);
        return new ResponseEntity<ServiceRequest>(newSr, HttpStatus.OK);

    }


    /**
     * Fetch a list of persisted service requests for a property
     * @param propertyId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchServiceRequests",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ServiceRequest>> fetchServiceRequests(
        @RequestParam long propertyId
    ) throws SQLException {
        
        List<ServiceRequest> srList = srService.fetchSrByPropertyId(propertyId);
        return new ResponseEntity<List<ServiceRequest>>(srList, HttpStatus.OK);

    }
    

    /**
     * Fetch a list of persisted service requests by status for a property
     * @param propertyId
     * @param status
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchServiceRequestsByStatus",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ServiceRequest>> fetchServiceRequestsByStatus(
        @RequestParam long propertyId,
        @RequestParam String status
    ) throws SQLException {

        List<ServiceRequest> srList = srService.fetchSrByPropertyIdAndStatus(propertyId, status);
        return new ResponseEntity<List<ServiceRequest>>(srList, HttpStatus.OK);

    }


    /**
     * Fetch a list of persisted service requests by type for a property
     * @param propertyId
     * @param type
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchServiceRequestsByType",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ServiceRequest>> fetchServiceRequestsByType(
        @RequestParam long propertyId,
        @RequestParam String type
    ) throws SQLException {

        List<ServiceRequest> srList = srService.fetchSrByPropertyIdAndType(propertyId, type);
        return new ResponseEntity<List<ServiceRequest>>(srList, HttpStatus.OK);

    }


    /**
     * Fetch a persisted service request by its ID
     * @param srId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchServiceRequestById",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ServiceRequest> fetchServiceRequestById(
        @RequestParam long srId
    ) throws SQLException {
        
        ServiceRequest sr = srService.fetchSrBySrId(srId).get();
        return new ResponseEntity<ServiceRequest>(sr, HttpStatus.OK);

    }


    /**
     * Update a persisted service request's status
     * @param srId
     * @param status
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updateServiceRequestStatus",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ServiceRequest> updateServiceRequestStatus(
        @RequestParam long srId,
        @RequestParam String status
    ) throws SQLException {

        ServiceRequest sr = srService.updateSrStatusBySrId(srId, status);
        return new ResponseEntity<ServiceRequest>(sr, HttpStatus.OK);

    }


    /**
     * Delete a persisted service request by its ID
     * @param srId
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/deleteServiceRequestById")
    public ResponseEntity<?> deleteServiceRequestById(
        @RequestParam long srId
    ) throws SQLException {

        srService.deleteSrBySrId(srId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
