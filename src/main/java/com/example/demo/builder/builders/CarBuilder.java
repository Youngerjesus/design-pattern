package com.example.demo.builder.builders;

import com.example.demo.builder.cars.CarType;
import com.example.demo.builder.components.Engine;
import com.example.demo.builder.components.GPSNavigator;
import com.example.demo.builder.components.Transmission;
import com.example.demo.builder.components.TripComputer;

public interface CarBuilder {
    void setCarType(CarType carType);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
