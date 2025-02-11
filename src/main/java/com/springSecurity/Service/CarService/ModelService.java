package com.springSecurity.Service.CarService;

import com.springSecurity.entity.Cars.Model;
import com.springSecurity.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {


    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public String addmodels(Model models) {
        Optional<Model> opModel = modelRepository.findByModelName(models.getModelName());
        if(opModel.isPresent()){
            throw new RuntimeException("Model is Already exist");
        }
        modelRepository.save(models);
        return "Model Saved";
    }

    public List<String> getall() {
        return modelRepository.findAllModelNames();
    }

}
