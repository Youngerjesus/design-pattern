package com.example.demo.visitor;

import com.example.demo.visitor.shapes.*;
import com.example.demo.visitor.visitor.XMLExportVisitor;

public class Demo {
    public static void main(String[] args) {
        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(dot);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        CompoundShape cp = new CompoundShape(5);
        cp.add(dot);
        compoundShape.add(cp);

        export(circle, compoundShape);
    }

    private static void export(Shape... args) {
        XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();
        System.out.println(xmlExportVisitor.export(args));
    }
}
