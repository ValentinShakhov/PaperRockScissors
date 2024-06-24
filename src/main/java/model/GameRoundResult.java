package model;

import java.util.Map;

public enum GameRoundResult {
    DRAW,
    WIN,
    LOSS;

    private static final Map<Shape, Shape> BEATS_MAP = Map.of(Shape.PAPER, Shape.ROCK,
            Shape.ROCK, Shape.SCISSORS,
            Shape.SCISSORS, Shape.PAPER);

    public static GameRoundResult calculate(Shape userShape, Shape opponentShape) {
        if (userShape == opponentShape) {
            return DRAW;
        }

        if (BEATS_MAP.get(userShape) == opponentShape) {
            return WIN;
        }

        return LOSS;
    }
}