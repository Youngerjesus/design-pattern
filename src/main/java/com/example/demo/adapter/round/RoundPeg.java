package com.example.demo.adapter.round;

/*
 * RoundPegs are compatible with RoundHoles.
 */
public class RoundPeg {
    private double radius;

    public RoundPeg(){}

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }
}
