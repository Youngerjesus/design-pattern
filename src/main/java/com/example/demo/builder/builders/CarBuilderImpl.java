package com.example.demo.builder.builders;

import com.example.demo.builder.cars.Car;
import com.example.demo.builder.cars.CarType;
import com.example.demo.builder.components.Engine;
import com.example.demo.builder.components.GPSNavigator;
import com.example.demo.builder.components.Transmission;
import com.example.demo.builder.components.TripComputer;

public class CarBuilderImpl implements CarBuilder{
    private CarType carType;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {

    }

    public void setGpsNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Car build(){
        return new Car(carType,seats,engine,transmission,tripComputer,gpsNavigator);
    }
}
