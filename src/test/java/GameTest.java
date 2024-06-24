import model.GameRoundResult;
import model.Shape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.OpponentService;
import service.UserInteractionService;
import state.GameState;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private UserInteractionService userInteractionService;

    @Mock
    private OpponentService opponentService;

    @Mock
    private GameState gameState;

    @InjectMocks
    private Game game;

    @Test
    public void testSingleRound() {
        Shape useShape = Shape.PAPER;
        Shape opponentShape = Shape.SCISSORS;
        GameRoundResult gameRoundResult = GameRoundResult.DRAW;

        when(userInteractionService.getShapeInput()).thenReturn(useShape);
        when(opponentService.getShapeInput()).thenReturn(opponentShape);
        when(gameState.endRound(useShape, opponentShape)).thenReturn(gameRoundResult);

        game.start();

        verify(userInteractionService).displayRoundNumber();
        verify(userInteractionService).displayRoundResult(gameRoundResult, opponentShape);
        verify(userInteractionService).getContinueGameInput();
        verify(userInteractionService).shutDown();
    }
}