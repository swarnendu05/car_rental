package com.realProject.controller;


import com.realProject.entity.Evaluation.CarEvaluationPhoto;
import com.realProject.service.CarEvaluationPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/car-photos")
public class CarEvaluationPhotoController {

    private final CarEvaluationPhotoService carEvaluationPhotoService;

    @Autowired
    public CarEvaluationPhotoController(CarEvaluationPhotoService carEvaluationPhotoService) {
        this.carEvaluationPhotoService = carEvaluationPhotoService;
    }



    @PostMapping("/upload/{evaluationId}")
    public ResponseEntity<List<CarEvaluationPhoto>> uploadCarPhotos(@PathVariable Long evaluationId,
                                                                    @RequestParam("files") List<MultipartFile> files) {
        List<CarEvaluationPhoto> uploadedPhotos = carEvaluationPhotoService.uploadCarPhotos(files, evaluationId);
        return ResponseEntity.ok(uploadedPhotos);
    }
}
