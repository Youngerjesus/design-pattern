package com.example.demo.factory_method.example_spring.food.service;

import com.example.demo.factory_method.example_spring.food.FoodType;
import org.springframework.stereotype.Service;

@Service
public class SnackService implements FoodService {
    @Override
    public FoodType getFoodType() {
        return FoodType.SNACK;
    }

    @Override
    public void deliverItem() {
        System.out.println("스낵 배달");
    }
}
