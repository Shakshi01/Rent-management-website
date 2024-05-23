package com.simplilease.server.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilease.server.Entities.ServiceRequest;
import com.simplilease.server.Repositories.ServiceRequestRepository;

@Service
public class ServiceRequestService {
    
    @Autowired
    private ServiceRequestRepository srRepo;

    /**
     * Create & persist a service request
     * @param sr
     * @return
     * @throws SQLException
     */
    public ServiceRequest createServiceRequest(ServiceRequest sr) throws SQLException {

        ServiceRequest newSr = srRepo.save(sr);
        return newSr;

    }

    /**
     * Fetch persisted service requests for a property
     * @param pId
     * @return
     * @throws SQLException
     */
    public List<ServiceRequest> fetchSrByPropertyId(long pId) throws SQLException {

        List<ServiceRequest> srList = srRepo.findByPid(pId);
        return srList;

    }


    /**
     * Fetch persisted service requests for property by status
     * @param pId
     * @param status
     * @return
     * @throws SQLException
     */
    public List<ServiceRequest> fetchSrByPropertyIdAndStatus(
        long pId,
        String status
    ) throws SQLException {

            List<ServiceRequest> srList = srRepo.findByPidAndStatus(pId, status);
            return srList;

    }


    /**
     * Fetch persisted service requests for property by type
     * @param pId
     * @param type
     * @return
     * @throws SQLException
     */
    public List<ServiceRequest> fetchSrByPropertyIdAndType(
        long pId, 
        String type
    ) throws SQLException {

        List<ServiceRequest> srList = srRepo.findByPidAndType(pId, type);
        return srList;

    }


    /**
     * Fetch a persisted service request by its ID
     * @param srId
     * @return
     * @throws SQLException
     */
    public Optional<ServiceRequest> fetchSrBySrId(long srId) throws SQLException {

        Optional<ServiceRequest> oSr = srRepo.findById(srId);
        return oSr;

    }


    /**
     * Update the status of a persisted service request by its ID
     * @param srId
     * @param newStatus
     * @return
     * @throws SQLException
     */
    public ServiceRequest updateSrStatusBySrId(long srId, String newStatus) throws SQLException {

        // fetch the sr
        ServiceRequest sr = srRepo.findById(srId).get();

        //update status
        sr.setStatus(newStatus);

        // save and return updated sr
        return srRepo.save(sr);

    }


    /**
     * Delete a peristsed service request by its ID
     * @param srId
     * @throws SQLException
     */
    public void deleteSrBySrId(long srId) throws SQLException {
        srRepo.deleteById(srId);
    }

}
