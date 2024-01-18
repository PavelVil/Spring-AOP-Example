package com.github.pavelvil.aop.example.service;

import com.github.pavelvil.aop.example.exception.ApplicationException;
import com.github.pavelvil.aop.example.model.Plant;

import java.util.List;

public interface PlantService {

    void addPlant(Plant plant) throws ApplicationException;

    void addPlants(List<Plant> plants) throws ApplicationException;

    List<Plant> getPlantsByType(String type) throws ApplicationException;

    Plant getPlantByName(String name) throws ApplicationException;

    int getPlantsCount();

}
