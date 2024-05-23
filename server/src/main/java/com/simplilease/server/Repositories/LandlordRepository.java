package com.simplilease.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilease.server.Entities.Landlord;
import java.util.Optional;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {
    
    Optional<Landlord> findByUname(String uname);

    Optional<Landlord> findByEmail(String email);

    Optional<Landlord> findBypNumber(String pnumber);

}
