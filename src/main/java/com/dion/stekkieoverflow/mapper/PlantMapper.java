package com.dion.stekkieoverflow.mapper;

import com.dion.stekkieoverflow.domain.Plant;
import com.dion.stekkieoverflow.dto.PlantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlantMapper {

    PlantMapper INSTANCE = Mappers.getMapper( PlantMapper.class);

    PlantDTO plantToPlantDTO(Plant plant);


    List<PlantDTO> plantToPlantDtos(List<Plant> answers);
}
