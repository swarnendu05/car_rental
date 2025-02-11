package com.springSecurity.controller;


import com.springSecurity.entity.Cars.*;
import com.springSecurity.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelRepository modelRepository;
//    @Autowired
//    private YearRepository yearRepository;
//    @Autowired
//    private TransmissionRepository transmissionRepository;
//    @Autowired
   // private FuelTypeRepository fuelTypeRepository;

//    @PostMapping
//    public Car createCar(@RequestBody Car car) {
//        // Fetch and attach existing entities
//
//
//        car.setBrand(brandRepository.findById(car.getBrand().getId()).orElseThrow(() -> new RuntimeException("Brand not found")));
//        car.setModel(modelRepository.findById(car.getModel().getId()).orElseThrow(() -> new RuntimeException("Model not found")));
//        car.setYear(yearRepository.findById(car.getYear().getId()).orElseThrow(() -> new RuntimeException("Year not found")));
//        car.setTransmission(transmissionRepository.findById(car.getTransmission().getId()).orElseThrow(() -> new RuntimeException("Transmission not found")));
//        car.setFuelType(fuelTypeRepository.findById(car.getFuelType().getId()).orElseThrow(() -> new RuntimeException("FuelType not found")));
//
//        return carRepository.save(car);
//    }
}
