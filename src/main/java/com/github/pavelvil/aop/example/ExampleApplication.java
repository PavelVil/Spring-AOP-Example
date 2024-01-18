package com.github.pavelvil.aop.example;

import com.github.pavelvil.aop.example.bean.example.IExampleBean;
import com.github.pavelvil.aop.example.model.Plant;
import com.github.pavelvil.aop.example.service.PlantService;
import com.github.pavelvil.aop.example.utils.ThreadUtils;
import com.github.pavelvil.aop.example.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class ExampleApplication {

//	@Autowired
//	private IExampleBean bean;

	@Autowired
	private PlantService plantService;

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
//		bean.info();

		UserContext.setUsername("admin");

		System.out.println(plantService.getPlantsCount());

		plantService.addPlant(new Plant("Роза", "Цветок"));
		ThreadUtils.waitTime(200);

		System.out.println(plantService.getPlantsCount());
		System.out.println(plantService.getPlantsByType("Цветок"));
		System.out.println(plantService.getPlantByName("Роза"));

		plantService.addPlants(List.of(new Plant("Кукуруза", "Трава"), new Plant("Дуб", "Дерево")));
		ThreadUtils.waitTime(200);

		System.out.println(plantService.getPlantsCount());
		System.out.println(plantService.getPlantsByType("Трава"));
		System.out.println(plantService.getPlantByName("Кукуруза"));
	}
}
