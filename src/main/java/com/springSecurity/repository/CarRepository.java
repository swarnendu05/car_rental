package com.springSecurity.repository;

import com.springSecurity.entity.Cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {



    @Query("SELECT c FROM Car c JOIN c.brand b Join c.transmission t " +
            "WHERE b.brandName = :details or t.type=:details")
    List<Car> searchCar(@Param("details") String brand);



}