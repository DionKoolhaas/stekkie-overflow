package com.dion.stekkieoverflow.service;

import com.dion.stekkieoverflow.dto.PlantDTO;
import com.dion.stekkieoverflow.mapper.PlantMapper;
import com.dion.stekkieoverflow.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<PlantDTO> getAllPlants() {
        return PlantMapper.INSTANCE.plantToPlantDtos(plantRepository.findAll());
    }
}
