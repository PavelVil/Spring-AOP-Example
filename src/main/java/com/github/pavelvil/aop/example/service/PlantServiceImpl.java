package com.github.pavelvil.aop.example.service;

import com.github.pavelvil.aop.example.annotation.Asynchronously;
import com.github.pavelvil.aop.example.annotation.PreInvoke;
import com.github.pavelvil.aop.example.annotation.SuccessLogging;
import com.github.pavelvil.aop.example.annotation.Valid;
import com.github.pavelvil.aop.example.exception.ApplicationException;
import com.github.pavelvil.aop.example.model.Plant;
import com.github.pavelvil.aop.example.model.RoleType;
import com.github.pavelvil.aop.example.utils.ThreadUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@SuccessLogging
public class PlantServiceImpl implements PlantService {

    private final Map<String, Plant> plants = new HashMap<>();

    @Override
    @Asynchronously
    @PreInvoke(roles = RoleType.ADMIN)
    public void addPlant(Plant plant) throws ApplicationException {
        plants.put(plant.getName(), plant);
    }

    @Override
    @Asynchronously
    @PreInvoke(roles = RoleType.ADMIN)
    public void addPlants(@Valid List<Plant> newPlants) throws ApplicationException {
        if (newPlants.size() == 1) {
            throw new ApplicationException("Используйте метод getPlant(Plant plant)");
        }

        plants.putAll(newPlants.stream().collect(Collectors.toMap(Plant::getName, Function.identity())));
    }

    @Override
    @PreInvoke(roles = {RoleType.USER, RoleType.ADMIN})
    public List<Plant> getPlantsByType(String type) throws ApplicationException {
        return plants.values().stream().filter(it -> it.getType().equals(type)).collect(Collectors.toList());
    }

    @Override
    @PreInvoke(roles = {RoleType.USER, RoleType.ADMIN})
    public Plant getPlantByName(String name) throws ApplicationException {
        return plants.get(name);
    }

    @Override
    public int getPlantsCount() {
        ThreadUtils.waitTime(100);
        return plants.size();
    }
}
