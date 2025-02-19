package com.springSecurity.repository;

import com.springSecurity.entity.Cars.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface YearRepository extends JpaRepository<Year, Long> {
    Optional<Year> findByYear(String year);

    @Query("Select b.year from  Year b")
    List<String> findAllYear();
}