package com.simplilease.server.Services;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilease.server.Entities.Landlord;
import com.simplilease.server.Repositories.LandlordRepository;

@Service
public class LandlordService {
    
    @Autowired
    private LandlordRepository llRepo;


    /**
     * Create and persist a landlord
     * @param ll
     * @return persisted landlord
     */
    public Landlord createLandlord(Landlord ll) {
        Landlord newLl = llRepo.save(ll);
        return newLl;
    }


    /**
     * Validates password for Landlord by username
     * @param uname
     * @param pswd
     * @return boolean
     */
    public boolean validateLogin(String uname, String pswd) {

        boolean validated = false;
        
        // fetch user
        Optional<Landlord> oll = llRepo.findByUname(uname);
        
        if (oll.isPresent()) {
            Landlord ll = llRepo.findByUname(uname).get();
            String actualPswd = ll.getPassword();

             // compare
            if (actualPswd.equals(pswd)) {
                validated = true;
            }

        }

        return validated;

    }


    /**
     * Fetch persisted landlord by llID
     * @param llId
     * @return
     * @throws SQLException
     */
    public Optional<Landlord> fetchLandlordById(long llId) throws SQLException {

        Optional<Landlord> oll = llRepo.findById(llId);
        return oll;

    }


    /**
     * Fetch a persisted landlord by username
     * @param uname
     * @return Landlord if exists
     * @throws SQLException
     */
    public Optional<Landlord> fetchLandlordByUname(String uname) throws SQLException {
        
        Optional<Landlord> oll = llRepo.findByUname(uname);
        return oll;

    }


    /**
     * Fetch a persisted landlord by email
     * @param email
     * @return
     * @throws SQLException
     */
    public Optional<Landlord> fetchLandlordByEmail(String email) throws SQLException {

        Optional<Landlord> oll = llRepo.findByEmail(email);
        return oll;
    }


    /**
     * Fetch a persisted landlord by phone number
     * @param pNumber
     * @return
     * @throws SQLException
     */
    public Optional<Landlord> fetchLandlordByPnumber(String pNumber) throws SQLException {

        Optional<Landlord> oll = llRepo.findBypNumber(pNumber);
        return oll;
    }


    /**
     * Delete a persisted landlord by llId
     * @param id
     * @throws SQLException
     */
    public void deleteLandlordById(long id) throws SQLException {
        llRepo.deleteById(id);
    }

}
