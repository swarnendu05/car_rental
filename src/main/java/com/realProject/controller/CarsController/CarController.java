package com.realProject.controller.CarsController;

import com.realProject.entity.Car.Car;
import com.realProject.repository.CarRepository;
import com.realProject.service.CarsServices.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    private CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //http://localhost:8080/api/v1/car/add
    @PostMapping("/add")
     public String addCarsdetails(
             @RequestBody Car car
    ){
        return carService.addCardetails(car);
    }

    //http://localhost:8080/api/v1/car/{id}
    @GetMapping("/getDetails/{id}")
    public Car getCardetails(
            @PathVariable Long id
    ){
        return carService.getCarDetailsById(id);
    }


    //http://localhost:8080/api/v1/car/searchCar?param=honda
    @GetMapping("/searchCar")

    public  List<Car> searchCar (
            @RequestParam String param
    ){
        return carService.searchCar(param);
    }

    //http://localhost:8080/api/v1/car?pageNo=0&pageSize=2
    @GetMapping()
    public List<Car> getAllCars(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "2", required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false) String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ){
        List<Car> cars = carService.getAllCars(pageNo,pageSize,sortBy,sortDir);

        return cars;
    }

}
