package com.springSecurity.repository;

import com.springSecurity.entity.Cars.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    Optional<Transmission> findByType(String TransmissionType);

    @Query("Select b.type from  Transmission b")
    List<String> findAllTransmission();
}