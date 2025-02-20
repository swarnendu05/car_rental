package com.realProject.repository;

import com.realProject.entity.Evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByPinCode(String pinCode);
}