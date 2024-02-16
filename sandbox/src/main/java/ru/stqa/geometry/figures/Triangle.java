package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if ((a < 0 || b < 0 || c < 0) || (a + b < c || b + c < a || a + c < b)) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
                                  //abc
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                                 //cba
                ||(Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                                //bac
                ||(Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                                //cab
                ||(Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)
                               //bca
                ||(Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                                //acb
                ||(Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
