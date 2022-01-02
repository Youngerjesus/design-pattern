package com.example.demo.factory_method.example_spring.food.service;

import com.example.demo.factory_method.example_spring.food.FoodType;
import org.springframework.stereotype.Service;

@Service
public class NoodleService implements FoodService{
    @Override
    public FoodType getFoodType() {
        return FoodType.NOODLE;
    }

    @Override
    public void deliverItem() {
        System.out.println("누들 배달");
    }
}
