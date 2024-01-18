package com.github.pavelvil.aop.example.aspect;

import com.github.pavelvil.aop.example.exception.ApplicationException;
import com.github.pavelvil.aop.example.model.Plant;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(3)
public class ValidateAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ValidateAspect.class);

    private static final Map<String, List<String>> PLANTS = new HashMap<>();

    static {
        PLANTS.put("Цветок", List.of("Роза", "Тюльпан", "Маргаритка"));
        PLANTS.put("Дерево", List.of("Дуб", "Сосна", "Береза"));
        PLANTS.put("Трава", List.of("Пшеница", "Кукуруза", "Рожь"));
    }

    @Pointcut("execution(public void add*(@com.github.pavelvil.aop.example.annotation.Valid (*), ..))")
    public void needValidPointcut() {}

    @Pointcut("@args(com.github.pavelvil.aop.example.annotation.Model) && execution(public void add*(..))")
    public void needValidPointcutWithAtArgs() {}

    @Pointcut("needValidPointcutWithAtArgs() && args(plant)")
    public void validatePlantPointcut(Plant plant) {}

    @Before("validatePlantPointcut(plant)")
    public void validateSinglePlant(Plant plant) {
        LOG.info("Валидация растения перед вызовом метода");
        validate(plant);
    }

    @Pointcut("needValidPointcut() && args(plants)")
    public void validatePlantListPointcut(List<Plant> plants) {}

    @Before("validatePlantListPointcut(plants)")
    public void validatePlantList(List<Plant> plants) {
        LOG.info("Валидация списка растений перед вызовом метода");
        plants.forEach(this::validate);
    }

    private void validate(Plant plant) {
        if (!PLANTS.containsKey(plant.getType()) ||
        PLANTS.get(plant.getType()).stream().noneMatch(it -> it.equals(plant.getName()))) {
            throw new ApplicationException("Ошибка валидации!");
        }
    }

}
