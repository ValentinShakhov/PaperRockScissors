package model;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShapeTest {

    @Test
    public void shouldReturnPaper() {
        Optional<Shape> existingKey = Shape.getShapeByKey("paper");
        assertTrue(existingKey.isPresent());
        assertEquals(Shape.PAPER, existingKey.get());
    }

    @Test
    public void shouldReturnRock() {
        Optional<Shape> existingKey = Shape.getShapeByKey("rock");
        assertTrue(existingKey.isPresent());
        assertEquals(Shape.ROCK, existingKey.get());
    }

    @Test
    public void shouldReturnScissors() {
        Optional<Shape> existingKey = Shape.getShapeByKey("scissors");
        assertTrue(existingKey.isPresent());
        assertEquals(Shape.SCISSORS, existingKey.get());
    }

    @Test
    public void shouldReturnEmpty() {
        Optional<Shape> nonExistingKey = Shape.getShapeByKey("nonExistingKey");
        assertTrue(nonExistingKey.isEmpty());
    }
}