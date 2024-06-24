import com.google.inject.Guice;
import com.google.inject.Inject;
import model.GameRoundResult;
import model.Shape;
import service.OpponentService;
import service.UserInteractionService;
import state.GameState;

public class Game {

    private final UserInteractionService userInteractionService;
    private final OpponentService opponentService;
    private final GameState gameState;

    @Inject
    public Game(UserInteractionService userInteractionService,
                OpponentService opponentService,
                GameState gameState) {
        this.userInteractionService = userInteractionService;
        this.opponentService = opponentService;
        this.gameState = gameState;
    }

    public static void main(final String[] args) {
        Guice.createInjector().getInstance(Game.class).start();
    }

    public void start() {
        do {
            userInteractionService.displayRoundNumber();

            Shape userShape = userInteractionService.getShapeInput();
            Shape opponentShape = opponentService.getShapeInput();
            GameRoundResult gameRoundResult = gameState.endRound(userShape, opponentShape);

            userInteractionService.displayRoundResult(gameRoundResult, opponentShape);
        } while (userInteractionService.getContinueGameInput());

        userInteractionService.shutDown();
        userInteractionService.displayGameResults();
    }
}