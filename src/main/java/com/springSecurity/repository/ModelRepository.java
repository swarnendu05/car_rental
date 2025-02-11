package com.springSecurity.repository;

import com.springSecurity.entity.Cars.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByModelName(String modelnames);

    @Query("Select b.modelName from  Model b")
    List<String> findAllModelNames();
}