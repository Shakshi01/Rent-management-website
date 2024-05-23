package com.simplilease.server.Controllers;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilease.server.Entities.Landlord;
import com.simplilease.server.Services.LandlordService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/server/landlordController")
public class LandlordController {
    
    @Autowired
    private LandlordService llService;

    /**
     * Create & persist a landlord
     * @param ll
     * @return
     * @throws SQLException
     */
    @PostMapping(
        path = "/createLandlord",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createLandlord(@RequestBody Landlord ll) throws SQLException {

        // init response
        ResponseEntity<?> response;

        String uname = ll.getUname();
        String email = ll.getEmail();
        String pnumber = ll.getPnumber();

        // check user name
        if (llService.fetchLandlordByUname(uname).isPresent()) {
            String responseBody = "Username is taken";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        // check email
        else if (llService.fetchLandlordByEmail(email).isPresent()) {
            String responseBody = "Email is taken";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        else if (llService.fetchLandlordByPnumber(pnumber).isPresent()) {
            String responseBody = "Phone number is taken";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        else {
            Landlord newLl = llService.createLandlord(ll);
            response = new ResponseEntity<Landlord>(newLl, HttpStatus.OK);
        }

        return response;

    }


    /**
     * Validate login attempt
     * @param uname
     * @param pswd
     * @return HTTP status 200 for OK or 401 for unauthorized
     */
    @GetMapping(
        path = "/login",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> tenantLogin(
        @RequestParam String uname,
        @RequestParam String pswd
    ) throws SQLException{
        
        ResponseEntity<?> response;
        boolean validated = llService.validateLogin(uname, pswd);
        
        if (validated) {
            Landlord ll = llService.fetchLandlordByUname(uname).get();
            response = new ResponseEntity<Landlord>(ll, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }
    
    
    /**
     * Fetch a persisted landlord by username
     * @param uname
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchLandlordByUsername",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchLandlordByUsername(@RequestParam String uname) throws SQLException {
        
        ResponseEntity<?> response;

        // fetch optional object
        Optional<Landlord> oll = llService.fetchLandlordByUname(uname);

        // validate it exists
        if (oll.isPresent()) {
            Landlord ll = oll.get();
            response = new ResponseEntity<Landlord>(ll, HttpStatus.OK);
        }
        else{
            String responseBody = "Landlord with username " + uname + " does not exist";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    

    /**
     * Fetch a persisted landlord by email
     * @param email
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchLandlordByEmail",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchLandlordByEmail(@RequestParam String email) throws SQLException {
        
        ResponseEntity<?> response;

        // fetch optional object
        Optional<Landlord> oll = llService.fetchLandlordByEmail(email);

        // validate if exists
        if (oll.isPresent()) {
            Landlord ll = oll.get();
            response = new ResponseEntity<Landlord>(ll, HttpStatus.OK);
        }
        else {
            String responseBody = "Landlord with email " + email + " does not exist";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        
        return response;
    }
    

    /**
     * Fetch a persisted landlord by phone number
     * @param pNumber
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchLandlordByPnumber",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchLandlordByPnumber(@RequestParam String pNumber) throws SQLException {
        
        ResponseEntity<?> response;

        // fetch optional object
        Optional<Landlord> oll = llService.fetchLandlordByPnumber(pNumber);

        // validate if exists
        if (oll.isPresent()) {
            Landlord ll = oll.get();
            response = new ResponseEntity<Landlord>(ll, HttpStatus.OK);
        }
        else {
            String responseBody = "Landlord with phone number " + pNumber + " does not exist";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return response;
    }


    /**
     * Delete a pesisted landlord by ID
     * @param id
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/deleteLandlordById")
    public ResponseEntity<?> deleteLandlordById(long id) throws SQLException {

        llService.deleteLandlordById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
