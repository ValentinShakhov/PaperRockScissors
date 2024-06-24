package model;

import java.util.Arrays;
import java.util.Optional;

public enum Shape {

    PAPER("paper"),
    ROCK("rock"),
    SCISSORS("scissors");

    private final String key;

    Shape(final String key) {
        this.key = key;
    }

    public static Optional<Shape> getShapeByKey(String key) {
        return Arrays.stream(Shape.values()).filter(shape -> shape.getKey().equals(key)).findFirst();
    }

    public String getKey() {
        return this.key;
    }
}
