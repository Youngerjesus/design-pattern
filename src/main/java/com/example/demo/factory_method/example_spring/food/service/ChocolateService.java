package com.example.demo.factory_method.example_spring.food.service;

import com.example.demo.factory_method.example_spring.food.FoodType;
import org.springframework.stereotype.Service;

@Service
public class ChocolateService implements FoodService {
    @Override
    public FoodType getFoodType() {
        return FoodType.CHOCOLATE;
    }

    @Override
    public void deliverItem() {
        System.out.println("초콜릿 배달");
    }
}
