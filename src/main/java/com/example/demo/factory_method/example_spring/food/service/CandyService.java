package com.example.demo.factory_method.example_spring.food.service;

import com.example.demo.factory_method.example_spring.food.FoodType;
import org.springframework.stereotype.Service;

@Service
public class CandyService implements FoodService {
    @Override
    public FoodType getFoodType() {
        return FoodType.CANDY;
    }

    @Override
    public void deliverItem() {
        System.out.println("사탕 배달");
    }
}
