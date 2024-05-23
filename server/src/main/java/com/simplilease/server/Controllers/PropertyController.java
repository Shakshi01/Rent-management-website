package com.simplilease.server.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import com.simplilease.server.Entities.Property;
import com.simplilease.server.Services.PropertyService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/server/propertyController")
public class PropertyController {
    
    @Autowired
    private PropertyService pService;


    /**
     * Create and persist a new property
     * @param p
     * @return
     * @throws SQLException
     */
    @PostMapping(
        path = "/createProperty",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createProperty(@RequestBody Property p) throws SQLException {

        // init response
        ResponseEntity<?> response;

        // validate name available for user
        String n = p.getPname();
        long ownerId = p.getOwnerId();
        Optional<Property> op = pService.fetchPropertyByPname(ownerId, n);
        if (op.isPresent()) {
            response = new ResponseEntity<String>("Name is already used", HttpStatus.BAD_REQUEST);
        }
        else {
            pService.createProperty(p);
            response = new ResponseEntity<Property>(p, HttpStatus.OK);
        }

        return response;
    }
    

    /**
     * Fetch all persisted properties by ownerId
     * @param ownerId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchAllPropertiesByOwner",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchAllPropertiesByOwner(@RequestParam long ownerId) throws SQLException {
        
        // init response
        ResponseEntity<?> response;

        List<Property> pList = pService.fetchAllPropertiesByOwnerId(ownerId);
        response = new ResponseEntity<List<Property>>(pList, HttpStatus.OK);

        return response;

    }


    /**
     * Fetch a persisted property by name
     * @param pName
     * @param ownerId
     * @return
     * @throws SQLException
     */
    @GetMapping(
        path = "/fetchPropertyByName",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchPropertyByPname(
        @RequestParam String pName,
        @RequestParam long ownerId
    ) throws SQLException {
        
        // init response
        ResponseEntity<?> response;
        
        // collect property
        Optional<Property> op = pService.fetchPropertyByPname(ownerId, pName);

        //check
        if (op.isPresent()) {
            Property p = op.get();
            response = new ResponseEntity<Property>(p, HttpStatus.OK);
        }
        else {
            String responseBody = "Property not found";
            response = new ResponseEntity<String>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    
    
    /**
     * Update property description by property ID
     * @param pId
     * @param description
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updatePropertyDescription",
        consumes = MediaType.TEXT_PLAIN_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Property> updatePropertyDescription(
        @RequestParam long pId,
        @RequestParam long ownerId,
        @RequestBody String description
    ) throws SQLException {

        // update description
        Property p = pService.updatePropertyDescription(ownerId, pId, description);
        
        return new ResponseEntity<Property>(p, HttpStatus.OK);
    }


    /**
     * Update rent status to vacant by property ID
     * @param pId
     * @param ownerId
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updatePropertyRentStatusVacant",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Property> updatePropertyRentStatusVacant(
        @RequestParam long pId,
        @RequestParam long ownerId
    ) throws SQLException{
        
        // update rent status
        String rentStatus = "vacant";
        Property p = pService.updatePropertyRentStatus(ownerId, pId, rentStatus);
        
        return new ResponseEntity<Property>(p, HttpStatus.OK);
    }


    /**
     * Uodate rent status to leased by propert ID
     * @param pId
     * @param ownerId
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updatePropertyRentStatusLeased",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Property> updatePropertyRentStatusLeased(
        @RequestParam long pId,
        @RequestParam long ownerId
    ) throws SQLException{
        
        // update rent status
        String rentStatus = "leased";
        Property p = pService.updatePropertyRentStatus(ownerId, pId, rentStatus);
        
        return new ResponseEntity<Property>(p, HttpStatus.OK);
    }


    /**
     * Update 
     * @param pId
     * @param rentPrice
     * @return
     * @throws SQLException
     */
    @PutMapping(
        path = "/updatePropertyRentPrice",
        consumes = MediaType.TEXT_PLAIN_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Property> updatePropertyRentPrice(
        @RequestParam long pId,
        @RequestParam long ownerId,
        @RequestBody float rentPrice
    ) throws SQLException {

        Property p = pService.updateRentPrice(ownerId, pId, rentPrice);

        return new ResponseEntity<Property>(p, HttpStatus.OK);

    }


    /**
     * Delete a persisted property by propery ID
     * @param pId
     * @return
     * @throws SQLException
     */
    @DeleteMapping(
        path = "/deletePropertyByPId",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deletePropertyByPId(@RequestParam long pId) throws SQLException {

        pService.deletePropertyById(pId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
