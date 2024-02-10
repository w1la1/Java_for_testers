package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.);
        Square.printSquareArea(8.5);
        Square.printSquareArea(5.5);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);


        Triangle.printTrianglePerimeter(5., 6., 7.);
        Triangle.printTriangleSquare(5., 6., 7.);

    }


}
