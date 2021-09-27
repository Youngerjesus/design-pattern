package com.example.demo.visitor.shapes;

import com.example.demo.visitor.visitor.Visitor;

public interface Shape {
    void move(int x, int y);
    void draw();

    String accept(Visitor visitor);
}
