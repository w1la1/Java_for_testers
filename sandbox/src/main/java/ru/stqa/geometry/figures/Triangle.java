package ru.stqa.geometry.figures;

public class Triangle {


    public static void printTrianglePerimeter(double a, double b, double c) {
        String text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f  ", a, b, c, perimeter(a, b, c));
        System.out.println(text);
    }

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    static double triangleHalfPerimeter(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    static double square(double a, double b, double c) {
        // S = √(p * (p — a) * (p — b) * (p — c))
        return Math.sqrt((triangleHalfPerimeter(a, b, c) * (triangleHalfPerimeter(a, b, c) - a) * (triangleHalfPerimeter(a, b, c) - b) * (triangleHalfPerimeter(a, b, c) - c)));
    }

    public static void printTriangleSquare(double a, double b, double c) {
        String text = String.format("Площадь треугольника со сторонами %f и %f и %f = %f ", a, b, c, square(a, b, c));
        System.out.println(text);
    }
}
