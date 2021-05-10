package com.example.demo.adapter;

import com.example.demo.adapter.adapters.SquarePegAdapter;
import com.example.demo.adapter.round.RoundHole;
import com.example.demo.adapter.round.RoundPeg;
import com.example.demo.adapter.square.SquarePeg;

import java.lang.reflect.Parameter;

public class Demo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg peg = new RoundPeg(5);

        if(hole.fits(peg)){
            System.out.println("Round Peg 45 fits round hole r5");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);

        SquarePegAdapter smallPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largePegAdapter = new SquarePegAdapter(largeSqPeg);

        if(hole.fits(smallPegAdapter)){
            System.out.println("Square peg w2 fits round hole r5");
        }
        if(!hole.fits(largePegAdapter)){
            System.out.println("Square peg w20 does not fit into round hole r5");
        }
    }
}
