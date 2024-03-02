package com.dion.stekkieoverflow.controller;

import com.dion.stekkieoverflow.dto.PlantDTO;
import com.dion.stekkieoverflow.mappers.PlantMapper;
import com.dion.stekkieoverflow.repository.PlantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private PlantRepository plantRepository;

    public PlantController(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    @GetMapping
    public List<PlantDTO> getAllPlants() {
        return PlantMapper.INSTANCE.plantToPlantDtos(plantRepository.findAll());
    }

}
