package state;

import model.GameRoundResult;
import model.Shape;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertSame;

public class GameStateTest {

    @Test
    public void testEndRoundAndGetStatistics() {
        GameState gameState = new GameState();

        gameState.endRound(Shape.PAPER, Shape.ROCK);
        gameState.endRound(Shape.PAPER, Shape.ROCK);
        gameState.endRound(Shape.PAPER, Shape.ROCK);
        gameState.endRound(Shape.PAPER, Shape.SCISSORS);
        gameState.endRound(Shape.PAPER, Shape.SCISSORS);
        gameState.endRound(Shape.PAPER, Shape.PAPER);

        Map<GameRoundResult, Long> statistics = gameState.getStatistics();

        assertSame(3L, statistics.get(GameRoundResult.WIN));
        assertSame(2L, statistics.get(GameRoundResult.LOSS));
        assertSame(1L, statistics.get(GameRoundResult.DRAW));
    }
}