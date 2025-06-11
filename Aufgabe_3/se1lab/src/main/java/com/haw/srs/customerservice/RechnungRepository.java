package com.haw.srs.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RechnungRepository extends JpaRepository<Rechnung, Long> {

    Optional<Rechnung> findById(Long id);
}
