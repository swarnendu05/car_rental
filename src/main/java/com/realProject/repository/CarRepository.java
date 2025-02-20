package com.realProject.repository;

import com.realProject.entity.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            " SELECT c FROM Car c " +
                    " JOIN c.brand b " +
                    "JOIN c.transmission t " +
            "WHERE b.name = :details OR t.type = :details"
    )
    List<Car> searchCar(
            @Param("details") String details
    );
}