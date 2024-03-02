package com.dion.stekkieoverflow.repository;

import com.dion.stekkieoverflow.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
