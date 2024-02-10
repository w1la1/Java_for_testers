package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter () {
        Assertions.assertEquals(18.,Triangle.perimeter(5.,6.,7));
    }
    @Test
    void canCalculateSquare() {
        Assertions.assertEquals(17.279999999999998,Triangle.area(7.2,6.,6.));
    }
}
