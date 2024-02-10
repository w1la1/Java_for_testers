package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(5., 6., 7.);
        Assertions.assertEquals(18., t.perimeter());
    }

    @Test
    void canCalculateSquare() {
        var p = new Triangle(7.2, 6., 6.);
        Assertions.assertEquals(17.279999999999998, p.area());
    }
}
