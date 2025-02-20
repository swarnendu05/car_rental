package com.realProject.repository;

import com.realProject.entity.Evaluation.CoustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoustomerVisitRepository extends JpaRepository<CoustomerVisit, Long> {
}