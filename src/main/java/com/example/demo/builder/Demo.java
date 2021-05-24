package com.example.demo.builder;

import com.example.demo.builder.builders.CarBuilder;
import com.example.demo.builder.builders.CarBuilderImpl;
import com.example.demo.builder.builders.CarManualBuilderImpl;
import com.example.demo.builder.cars.Car;
import com.example.demo.builder.cars.Manual;
import com.example.demo.builder.directors.Director;

public class Demo {
    public static void main(String[] args) {
        Director director = new Director();

        CarBuilderImpl builder = new CarBuilderImpl();
        director.constructSportsCar(builder);

        Car car = builder.build();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilderImpl manualBuilder = new CarManualBuilderImpl();

        director.constructSportsCar(manualBuilder);
        Manual carManual = manualBuilder.build();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }
}
