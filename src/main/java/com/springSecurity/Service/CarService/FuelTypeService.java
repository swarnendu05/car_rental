package com.springSecurity.Service.CarService;


import com.springSecurity.entity.Cars.FuelType;
import com.springSecurity.repository.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelTypeService {
      @Autowired
    private FuelTypeRepository fuelTypeRepository;

    public String addFuelType(FuelType fuelType) {
        Optional<FuelType> opTypes = fuelTypeRepository.findByFuelType(fuelType.getFuelType());
        if(opTypes.isPresent()){
            throw new RuntimeException("This Fuel_Type is already exist");
        }
        fuelTypeRepository.save(fuelType);
        return "Fuel_type Saved";
    }

    public List<String> getall() {
        return fuelTypeRepository.findAllFuelTypes();
    }


}
