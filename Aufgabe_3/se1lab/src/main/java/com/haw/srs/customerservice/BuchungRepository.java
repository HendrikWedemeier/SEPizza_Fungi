package com.haw.srs.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuchungRepository extends JpaRepository<Buchung, Long> {
    Optional<Buchung> findById(Long id);
}
