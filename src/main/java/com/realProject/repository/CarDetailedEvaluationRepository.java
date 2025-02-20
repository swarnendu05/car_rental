package com.realProject.repository;

import com.realProject.entity.Evaluation.CarDetailedEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDetailedEvaluationRepository extends JpaRepository<CarDetailedEvaluation, Long> {
}