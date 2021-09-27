package com.example.demo.visitor.visitor;

import com.example.demo.visitor.shapes.Circle;
import com.example.demo.visitor.shapes.CompoundShape;
import com.example.demo.visitor.shapes.Dot;
import com.example.demo.visitor.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundShape(CompoundShape compoundShape);
}
