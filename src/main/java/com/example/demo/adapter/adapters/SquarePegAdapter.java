package com.example.demo.adapter.adapters;

import com.example.demo.adapter.round.RoundPeg;
import com.example.demo.adapter.square.SquarePeg;

public class SquarePegAdapter extends RoundPeg {
    SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public double getRadius() {
        return Math.sqrt(Math.pow(squarePeg.getWidth() / 2, 2) * 2);
    }
}
