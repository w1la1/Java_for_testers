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

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 6.0, 7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithInequalitySide() {
        try {
            new Triangle(7.0, 2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }
}
