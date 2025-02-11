package com.springSecurity.repository;

import com.springSecurity.entity.Cars.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByBrandName(String brandname);

    @Query("Select b.brandName from  Brand b")
    List<String> findAllBrandNames();

}