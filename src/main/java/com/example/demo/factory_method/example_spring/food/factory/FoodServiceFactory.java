package com.example.demo.factory_method.example_spring.food.factory;

import com.example.demo.factory_method.example_spring.food.FoodType;
import com.example.demo.factory_method.example_spring.food.service.FoodService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FoodServiceFactory {
    private final Map<FoodType, FoodService> foodServices = new HashMap<>();

    public FoodServiceFactory(List<FoodService> foodServices) {
        if (CollectionUtils.isEmpty(foodServices)) {
            throw new IllegalArgumentException("존재하는 foodServices 없음");
        }

        for (FoodService foodService : foodServices) {
            this.foodServices.put(foodService.getFoodType(), foodService);
        }
    }

    public FoodService getService(FoodType foodType) {
        return this.foodServices.get(foodType);
    }
}
