package com.dion.stekkieoverflow.controller;

import com.dion.stekkieoverflow.dto.PlantDTO;
import com.dion.stekkieoverflow.service.PlantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for the Plants resource
 */
@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    /**
     * Get all plants, no pagination yet.
     * @return
     */
    @GetMapping
    public List<PlantDTO> getAllPlants() {
        return plantService.getAllPlants();
    }

}
