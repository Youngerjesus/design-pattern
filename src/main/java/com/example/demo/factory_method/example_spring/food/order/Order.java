package com.example.demo.factory_method.example_spring.food.order;

import com.example.demo.factory_method.example_spring.food.FoodType;

public class Order {
    FoodType foodType;

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
}
