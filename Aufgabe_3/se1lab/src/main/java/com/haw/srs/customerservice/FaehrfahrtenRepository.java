package com.haw.srs.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaehrfahrtenRepository extends JpaRepository<Faehrfahrt, Long> {
    Optional<Faehrfahrt> findById(Long id);
}
