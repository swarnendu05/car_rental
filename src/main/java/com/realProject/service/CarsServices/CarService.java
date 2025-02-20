package com.realProject.service.CarsServices;

import com.realProject.entity.Car.Car;
import com.realProject.entity.Evaluation.CarEvaluationPhoto;
import com.realProject.repository.CarEvaluationPhotoRepository;
import com.realProject.repository.CarRepository;
import com.realProject.service.S3Service1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public String addCardetails(Car car) {
        Car save = carRepository.save(car);
        return "Data is saved";
    }

    public Car getCarDetailsById(Long id) {
     Optional<Car> opCar = carRepository.findById(id);
     if(opCar.isPresent()){
         Car savCar = opCar.get();
         return savCar;
     }
     return null;
    }

    public List<Car> searchCar(String param) {
        List<Car> car = carRepository.searchCar(param);
        if(car.isEmpty()){
            throw new RuntimeException("No car found " + param);
        }
        return car;
    }

    public List<Car> getAllCars(int pageNo, int pageSize, String sortBy, String sortDir) {

        // Check if sorting by brand name or another property
        if ("brand".equalsIgnoreCase(sortBy)) {
            sortBy = "brand.name";  // Set the property of the brand object to sort by name
        }

        Sort sort = Sort.by(sortBy);

        // Handle the sorting direction
        if ("asc".equalsIgnoreCase(sortDir)) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        Pageable page = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Car> record = carRepository.findAll(page);
      List<Car> cars = record.getContent();
        return cars;
    }

    @Service
    public static class CarEvaluationPhotoService {

        private final CarEvaluationPhotoRepository carEvaluationPhotoRepository;
        private final S3Service1 s3Service1; // Service to upload images to S3

        @Autowired
        public CarEvaluationPhotoService(CarEvaluationPhotoRepository carEvaluationPhotoRepository, S3Service1 s3Service1) {
            this.carEvaluationPhotoRepository = carEvaluationPhotoRepository;
            this.s3Service1 = s3Service1;
        }

        public CarEvaluationPhoto uploadCarPhoto(MultipartFile file, Long evaluationId) {
            String photoUrl = s3Service1.uploadFiles(List.of(file)).get(0); // Upload file to S3 and get URL

            CarEvaluationPhoto carEvaluationPhoto = new CarEvaluationPhoto();
            carEvaluationPhoto.setPhotoUrl(photoUrl);
            // Here you should fetch CarDetailedEvaluation by evaluationId and set it
            // carEvaluationPhoto.setCarDetailedEvaluation(fetchedEvaluation);

            return carEvaluationPhotoRepository.save(carEvaluationPhoto);
        }
    }
}
