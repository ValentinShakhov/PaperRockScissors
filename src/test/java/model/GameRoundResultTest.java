package model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GameRoundResultTest {

    private final Shape userShape;
    private final Shape opponentShape;
    private final GameRoundResult expected;
    public GameRoundResultTest(Shape userShape,
                               Shape opponentShape,
                               GameRoundResult expected) {
        this.userShape = userShape;
        this.opponentShape = opponentShape;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Shape.PAPER, Shape.ROCK, GameRoundResult.WIN},
                {Shape.ROCK, Shape.SCISSORS, GameRoundResult.WIN},
                {Shape.SCISSORS, Shape.PAPER, GameRoundResult.WIN},
                {Shape.PAPER, Shape.SCISSORS, GameRoundResult.LOSS},
                {Shape.ROCK, Shape.PAPER, GameRoundResult.LOSS},
                {Shape.SCISSORS, Shape.ROCK, GameRoundResult.LOSS},
                {Shape.PAPER, Shape.PAPER, GameRoundResult.DRAW},
                {Shape.ROCK, Shape.ROCK, GameRoundResult.DRAW},
                {Shape.SCISSORS, Shape.SCISSORS, GameRoundResult.DRAW}
        });
    }

    @Test
    public void test() {
        assertEquals(expected, GameRoundResult.calculate(userShape, opponentShape));
    }
}