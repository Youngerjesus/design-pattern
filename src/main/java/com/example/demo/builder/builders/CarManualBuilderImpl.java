package com.example.demo.builder.builders;

import com.example.demo.builder.cars.Car;
import com.example.demo.builder.cars.CarType;
import com.example.demo.builder.cars.Manual;
import com.example.demo.builder.components.Engine;
import com.example.demo.builder.components.GPSNavigator;
import com.example.demo.builder.components.Transmission;
import com.example.demo.builder.components.TripComputer;

/*
 * Unlike other creational patterns, Builder can construct unrelated products, which don't have the common interface.
 * In this case we build a user manual for a car, using the same steps as we built a car.
 * This allows to produce manuals for specific car models, configured with different features.
 */
public class CarManualBuilderImpl implements CarBuilder {
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

    public Manual build(){
        return new Manual(carType,seats,engine,transmission,tripComputer,gpsNavigator);
    }
}
