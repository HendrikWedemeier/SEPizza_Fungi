package com.haw.srs.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HafenRepository extends JpaRepository<Hafen, GPSLocation> {
    Optional<Hafen> findByGpsLocation(GPSLocation gpsLocation);
}
