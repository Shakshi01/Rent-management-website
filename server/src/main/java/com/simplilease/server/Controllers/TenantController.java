package com.simplilease.server.Controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import com.simplilease.server.Entities.Tenant;
import com.simplilease.server.Services.TenantService;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/server/tenantController")
public class TenantController {
    
    @Autowired
    private TenantService tService;


    /**
     * Persist a new tenant
     * @param t
     * @return
     * @throws SQLException
     */
    @PostMapping(
        path = "/createTenant",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createTenant(@RequestBody Tenant t) throws SQLException {

        // init response
        ResponseEntity<?> response;
        String responseBody;

        // check username exists
        if (tService.fetchTenantByUname(t.getUname()).isPresent()) {
            responseBody = "Username is taken";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        // check email
        else if (tService.fetchTenantByEmail(t.getEmail()).isPresent()) {
            responseBody = "Email is taken";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        // check phone number
        else if (tService.fetchTenantByPnumber(t.getPnumber()).isPresent()) {
            responseBody = "Phone number is taken";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }
        // unique credentials are available
        else {
            Tenant newTenant = tService.createTenant(t);
            response = new ResponseEntity<Tenant>(newTenant, HttpStatus.OK);
        }

        return response;
    }


    /**
     * verfiy login
     * @param uname
     * @param pswd
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/login"
    )
    public ResponseEntity<?> login(
        @RequestParam String uname,
        @RequestParam String pswd
    ) throws SQLException {
        
        // init response
        ResponseEntity<?> response;
        boolean verified = tService.loginVerified(uname, pswd);

        if (verified) {
            Tenant t = tService.fetchTenantByUname(uname).get();
            response = new ResponseEntity<Tenant>(t, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return response;

    }


    /**
     * Admin request
     * Fetch all persisted tenants
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchAllTenants",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Tenant>> fetchAllTenants() throws SQLException {
        
        List<Tenant> tList = tService.fetchAllTenants();
        return new ResponseEntity<List<Tenant>>(tList, HttpStatus.OK);

    }


    /**
     * Fetch persisted tenant by username
     * @param uname
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchTenantByUname",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchTenantByUname(@RequestParam String uname) throws SQLException {
        
        // init response
        ResponseEntity<?> response;

        // fetch tenant
        Optional<Tenant> ot = tService.fetchTenantByUname(uname);
        if (ot.isPresent()) {
            Tenant t = ot.get();
            response = new ResponseEntity<Tenant>(t, HttpStatus.OK);
        }
        else {
            String responseBody = "Tenant is not found";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return response;
        
    }
    
    
    /**
     * Fetch persisted tenant by email
     * @param email
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchTenantByEmail",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchTenantByEmail(@RequestParam String email) throws SQLException {
        
        // init response
        ResponseEntity<?> response;

        // fetch tenant
        Optional<Tenant> ot = tService.fetchTenantByEmail(email);
        if (ot.isPresent()) {
            Tenant t = ot.get();
            response = new ResponseEntity<Tenant>(t, HttpStatus.OK);
        }
        else {
            String responseBody = "Tenant is not found";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return response;
        
    }


    /**
     * Fetch persisted tenants by property
     * @param pId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchTenantByProperty",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Tenant>> fetchTenantByProperty(@RequestParam long pId) throws SQLException {
        
        List<Tenant> tList = tService.fetchTenantsByPropertyId(pId);
        return new ResponseEntity<List<Tenant>>(tList, HttpStatus.OK);

    }


    /**
     * Add rental property for a persisted tenant
     * @param tId
     * @param pId
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updatePropertyRented",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Tenant> putMethodName(
        @RequestParam long tId,
        @RequestParam long pId
    ) throws SQLException {
        
        Tenant t = tService.updatePropertyRentedId(tId, pId);
        return new ResponseEntity<Tenant>(t, HttpStatus.OK);
    }
    
    
    /**
     * Delete a persisted tenant
     * @param tId
     * @return
     * @throws SQLException
     */
    @DeleteMapping(
        path = "/deleteTenantById"
    )
    public ResponseEntity<HttpStatus> deleteTenantById(@RequestParam long tId) throws SQLException {

        tService.deleteTenantById(tId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
