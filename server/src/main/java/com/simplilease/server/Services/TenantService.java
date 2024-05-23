package com.simplilease.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilease.server.Entities.Tenant;
import com.simplilease.server.Repositories.TenantRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {
    
    @Autowired
    private TenantRepository tRepo;

    /**
     * Persist a tenant
     * @param t
     * @return
     * @throws SQLException
     */
    public Tenant createTenant(Tenant t) throws SQLException{

        return tRepo.save(t);

    }


    /**
     * Check credentials at login
     * @param uname
     * @param pswd
     * @return
     * @throws SQLException
     */
    public boolean loginVerified(String uname, String pswd) throws SQLException {

        boolean verified = false;

        // check tenant username exists
        Optional<Tenant> ot = tRepo.findByUname(uname);
        if (ot.isPresent()) {
            String collectedPswd = ot.get().getPassword();
            // check password
            if (collectedPswd.equals(pswd)) {
                verified = true;
            }
            else {
                verified = false;
            }
        }

        return verified;

    }


    /**
     * Fetch all tenants
     * @return
     * @throws SQLException
     */
    public List<Tenant> fetchAllTenants() throws SQLException {
        
        List<Tenant> tList = tRepo.findAll();
        return tList;

    }


    public Optional<Tenant> fetchTenantById(long tId) throws SQLException {

        Optional<Tenant> ot = tRepo.findById(tId);
        return ot;

    }


    /**
     * Fetch persisted tenant by username
     * @param uname
     * @return
     * @throws SQLException
     */
    public Optional<Tenant> fetchTenantByUname(String uname) throws SQLException {

        Optional<Tenant> ot = tRepo.findByUname(uname);
        return ot;

    }


    /**
     * Fetch persisted tenant by email
     * @param email
     * @return
     * @throws SQLException
     */
    public Optional<Tenant> fetchTenantByEmail(String email) throws SQLException {

        Optional<Tenant> ot = tRepo.findByEmail(email);
        return ot;
    }


    /**
     * Fetch persisted tenant by phone number
     * @param pNumber
     * @return
     * @throws SQLException
     */
    public Optional<Tenant> fetchTenantByPnumber(String pNumber) throws SQLException {

        Optional<Tenant> ot = tRepo.findByPnumber(pNumber);
        return ot;

    }


    /**
     * Fetch persisted tenants by property ID
     * @param pId
     * @return
     * @throws SQLException
     */
    public List<Tenant> fetchTenantsByPropertyId(long pId) throws SQLException {

        List<Tenant> tList = tRepo.findByPropertyId(pId);
        return tList;

    }


    /**
     * Update tenant proprety rental by ID
     * @param tenantId
     * @param pId
     * @return
     * @throws SQLException
     */
    public Tenant updatePropertyRentedId(long tenantId, long pId) throws SQLException {
        
        Tenant t = tRepo.findById(tenantId).get();
        t.setPropertyId(pId);
        
        return tRepo.save(t);

    }


    /**
     * Delete persisted tenant by ID
     * @param tenantId
     * @throws SQLException
     */
    public void deleteTenantById(long tenantId) throws SQLException {

        tRepo.deleteById(tenantId);

    }

}
