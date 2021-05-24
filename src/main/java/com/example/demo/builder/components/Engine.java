package com.example.demo.builder.components;

public class Engine {
    private final double volume;
    private double mileage;
    private boolean started;

    public Engine(double volume, double mileage) {
        this.volume = volume;
        this.mileage = mileage;
    }

    public void on(){
        started = true;
    }

    public void off(){
        started = false;
    }

    public boolean isStarted(){
        return started;
    }

    public double getVolume() {
        return volume;
    }

    public double getMileage() {
        return mileage;
    }

    public void go(double mileage){
        if(started){
            this.mileage += mileage;
        }else{
            System.out.println("Cannot go(), you must start engine first!");
        }
    }


}
