package state;

import jakarta.inject.Singleton;
import model.GameRound;
import model.GameRoundResult;
import model.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class GameState {

    private final List<GameRound> results = new ArrayList<>();

    public int getRoundNumber() {
        return results.size() + 1;
    }

    public GameRoundResult endRound(Shape userShape, Shape opponentShape) {
        results.add(new GameRound(userShape, opponentShape));
        return GameRoundResult.calculate(userShape, opponentShape);
    }

    public Map<GameRoundResult, Long> getStatistics() {
        return results.stream().collect(Collectors.groupingBy(gameRound ->
                        GameRoundResult.calculate(gameRound.userShape(), gameRound.opponentShape()),
                Collectors.counting()));
    }
}
