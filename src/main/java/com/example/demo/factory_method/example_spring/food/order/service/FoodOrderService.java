package com.example.demo.factory_method.example_spring.food.order.service;

import com.example.demo.factory_method.example_spring.food.factory.FoodServiceFactory;
import com.example.demo.factory_method.example_spring.food.order.Order;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderService {
    private final FoodServiceFactory foodServiceFactory;

    public FoodOrderService(FoodServiceFactory foodServiceFactory) {
        this.foodServiceFactory = foodServiceFactory;
    }

    public void receiveOrder(Order order) {
        foodServiceFactory.getService(order.getFoodType()).deliverItem();
    }
}
