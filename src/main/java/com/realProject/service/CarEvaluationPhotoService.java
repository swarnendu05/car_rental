package com.realProject.service;

import com.realProject.entity.Evaluation.CarEvaluationPhoto;
import com.realProject.entity.Evaluation.CarDetailedEvaluation;
import com.realProject.repository.CarEvaluationPhotoRepository;
import com.realProject.repository.CarDetailedEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarEvaluationPhotoService {

    private final CarEvaluationPhotoRepository carEvaluationPhotoRepository;
    private final CarDetailedEvaluationRepository carDetailedEvaluationRepository;
    private final S3Service1 s3Service1;

    @Autowired
    public CarEvaluationPhotoService(CarEvaluationPhotoRepository carEvaluationPhotoRepository,
                                     CarDetailedEvaluationRepository carDetailedEvaluationRepository,
                                     S3Service1 s3Service1) {
        this.carEvaluationPhotoRepository = carEvaluationPhotoRepository;
        this.carDetailedEvaluationRepository = carDetailedEvaluationRepository;
        this.s3Service1 = s3Service1;
    }


    public List<CarEvaluationPhoto> uploadCarPhotos(List<MultipartFile> files, Long evaluationId) {
        Optional<CarDetailedEvaluation> evaluationOpt = carDetailedEvaluationRepository.findById(evaluationId);
        if (evaluationOpt.isEmpty()) {
            throw new RuntimeException("CarDetailedEvaluation with ID " + evaluationId + " not found.");
        }
        CarDetailedEvaluation evaluation = evaluationOpt.get();

        List<String> uploadedUrls = s3Service1.uploadFiles(files);

        List<CarEvaluationPhoto> savedPhotos = new ArrayList<>();

        for (String url : uploadedUrls) {
            CarEvaluationPhoto photo = new CarEvaluationPhoto();
            photo.setPhotoUrl(url);
            photo.setCarDetailedEvaluation(evaluation);
            savedPhotos.add(carEvaluationPhotoRepository.save(photo));
        }

        return savedPhotos;
    }
}
