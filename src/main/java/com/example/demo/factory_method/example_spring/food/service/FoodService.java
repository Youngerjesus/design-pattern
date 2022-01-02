package com.example.demo.factory_method.example_spring.food.service;

import com.example.demo.factory_method.example_spring.food.FoodType;

public interface FoodService {
    FoodType getFoodType();

    void deliverItem();
}
