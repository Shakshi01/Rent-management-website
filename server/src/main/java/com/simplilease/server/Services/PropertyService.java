package com.simplilease.server.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilease.server.Entities.Property;
import com.simplilease.server.Repositories.PropertyRepository;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository pRepo;

    
    /**
     * Create and persist a property
     * @param newProperty
     * @return
     * @throws SQLException
     */
    public Property createProperty(Property newProperty) throws SQLException {

        pRepo.save(newProperty);

        return newProperty;

    }


    /**
     * Fetch all properties owned by landlord
     * @param ownerId
     * @return
     * @throws SQLException
     */
    public List<Property> fetchAllPropertiesByOwnerId(long ownerId) throws SQLException {

        List<Property> pList = pRepo.findByOwnerId(ownerId);
        return pList;

    }


    /**
     * Fetch a persisted property by name
     * @param pName
     * @return
     * @throws SQLException
     */
    public Optional<Property> fetchPropertyByPname(long ownerId, String pName) throws SQLException {

        Optional<Property> op = pRepo.findByOwnerIdAndPname(ownerId, pName);
        return op;

    }


    /**
     * Update property description
     * @param pId
     * @param d
     * @return
     * @throws SQLException
     */
    public Property updatePropertyDescription(
        long ownerId,
        long pId,
        String d
    ) throws SQLException {

        Property p = pRepo.findByOwnerIdAndPid(ownerId, pId).get();
        p.setDescription(d);
        pRepo.save(p);

        return p;

    }


    /**
     * Update property rent status
     * @param pId
     * @param status
     * @return
     * @throws SQLException
     */
    public Property updatePropertyRentStatus(
        long ownerId,
        long pId,
        String status
    ) throws SQLException {

        Property p = pRepo.findByOwnerIdAndPid(ownerId, pId).get();
        p.setRentStatus(status);
        pRepo.save(p);

        return p;

    }


    /**
     * Update property rent price
     * @param pId
     * @param price
     * @return
     * @throws SQLException
     */
    public Property updateRentPrice(
        long ownerId,
        long pId,
        float price
    ) throws SQLException {

        Property p = pRepo.findByOwnerIdAndPid(ownerId, pId).get();
        p.setRentPrice(price);
        pRepo.save(p);

        return p;

    }


    /**
     * Delete a persisted property by property ID
     * @param pId
     * @throws SQLException
     */
    public void deletePropertyById(long pId) throws SQLException {

        pRepo.deleteById(pId);

    }

    
}
