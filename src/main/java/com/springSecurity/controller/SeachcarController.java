package com.springSecurity.controller;


import com.springSecurity.entity.Cars.Brand;
import com.springSecurity.entity.Cars.Car;
import com.springSecurity.repository.BrandRepository;
import com.springSecurity.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vi/search-car")
public class SeachcarController {



    private CarRepository carRepository;

    public SeachcarController( CarRepository carRepository) {

        this.carRepository = carRepository;
    }

   //   http://localhost:8080/api/vi/search-car/cars?name=automatic
    @GetMapping("/cars")
    public List<Car> searchCar(
            @RequestParam String name
    ){

        return  carRepository.searchCar(name);

    }
}
