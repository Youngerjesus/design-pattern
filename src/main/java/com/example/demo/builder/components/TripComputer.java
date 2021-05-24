package com.example.demo.builder.components;

import com.example.demo.builder.cars.Car;

public class TripComputer {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuelLevel(){
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus(){
        Engine engine = car.getEngine();
        if(engine.isStarted()){
            System.out.println("Car is started");
        }else{
            System.out.println("Car isn't started");
        }
    }

}
