package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {


    public static void printTrianglePerimeter(Triangle b) {
        String text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f ", b.a, b.b, b.c, b.perimeter());
        System.out.println(text);
    }


    double triangleHalfPerimeter() {
        return (this.a + this.b + this.c) / 2;
    }


    public static void printTriangleArea(Triangle a) {
        String text = String.format("Площадь треугольника со сторонами %f и %f и %f = %f ", a.a, a.b, a.c, a.area());
        System.out.println(text);
    }

    public double area() {
        // S = √(p * (p — a) * (p — b) * (p — c))
        return Math.sqrt((triangleHalfPerimeter() * (triangleHalfPerimeter() - this.a) * (triangleHalfPerimeter() - this.b) * (triangleHalfPerimeter() - this.c)));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }
}
