package service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import model.GameRoundResult;
import model.Shape;
import state.GameState;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Singleton
public class UserInteractionService {
    private static final String ROUND_WON_MESSAGE = "Round won";
    private static final String ROUND_TIE_MESSAGE = "Tie";
    private static final String ROUND_LOST_MESSAGE = "Round lost";
    private static final String INVALID_INPUT_MESSAGE = "Invalid input";
    private static final String CONTINUE_GAME_MESSAGE = "Continue? (y/n)";
    private static final String INSTRUCTIONS_MESSAGE = String.format("Type in your shape and press 'Enter'\nPossible input values: %s",
            Arrays.stream(Shape.values()).map(Shape::getKey).collect(Collectors.joining(", ")));
    private static final String ROUND_NUMBER_MESSAGE = "Round #";
    private static final String MESSAGE_PREFIX = "-> ";

    private final Scanner scanner = new Scanner(System.in);

    private final GameState gameState;

    @Inject
    public UserInteractionService(GameState gameState) {
        this.gameState = gameState;
    }

    public void shutDown() {
        scanner.close();
    }

    public Shape getShapeInput() {
        display(INSTRUCTIONS_MESSAGE);

        Shape result = null;

        while (scanner.hasNext()) {
            String userInput = scanner.next();
            Optional<Shape> userShape = Shape.getShapeByKey(userInput);

            if (userShape.isPresent()) {
                result = userShape.get();
                break;
            } else {
                displayInvalidShapeMessage();
            }
        }

        return result;
    }

    public boolean getContinueGameInput() {
        display(CONTINUE_GAME_MESSAGE);

        boolean result = true;

        while (scanner.hasNext()) {
            String userInput = scanner.next();

            if (userInput.equals("y")) {
                break;
            } else if (userInput.equals("n")) {
                result = false;
                break;
            } else {
                displayInvalidContinueMessage();
            }
        }

        return result;
    }

    public void displayRoundNumber() {
        display(String.format("%s%s", ROUND_NUMBER_MESSAGE, gameState.getRoundNumber()));
    }

    public void displayRoundResult(final GameRoundResult roundResult, Shape opponentShape) {
        display(String.format("Opponent: %s", opponentShape.getKey()));
        display(getRoundResultMessage(roundResult));
    }

    public void displayGameResults() {
        Map<GameRoundResult, Long> statistics = gameState.getStatistics();

        display(String.format("Won: %s, Lost: %s, Tied: %s",
                statistics.getOrDefault(GameRoundResult.WIN, 0L),
                statistics.getOrDefault(GameRoundResult.LOSS, 0L),
                statistics.getOrDefault(GameRoundResult.DRAW, 0L)));
    }

    private void displayInvalidShapeMessage() {
        display(INVALID_INPUT_MESSAGE);
        display(INSTRUCTIONS_MESSAGE);
    }

    private void displayInvalidContinueMessage() {
        display(INVALID_INPUT_MESSAGE);
        display(CONTINUE_GAME_MESSAGE);
    }

    private String getRoundResultMessage(GameRoundResult roundResult) {
        return switch (roundResult) {
            case WIN -> ROUND_WON_MESSAGE;
            case DRAW -> ROUND_TIE_MESSAGE;
            case LOSS -> ROUND_LOST_MESSAGE;
        };
    }

    private void display(final String message) {
        System.out.printf("%s %s%n", MESSAGE_PREFIX, message);
    }
}