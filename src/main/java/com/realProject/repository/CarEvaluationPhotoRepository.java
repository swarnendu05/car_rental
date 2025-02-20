package com.realProject.repository;

import com.realProject.entity.Evaluation.CarEvaluationPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEvaluationPhotoRepository extends JpaRepository<CarEvaluationPhoto, Long> {
}