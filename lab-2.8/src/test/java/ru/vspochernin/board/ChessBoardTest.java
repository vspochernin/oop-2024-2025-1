package ru.vspochernin.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

/**
 * @author pochernin-vla
 */
class ChessBoardTest extends TestWithBoard {

    @Test
    public void basicMoveValidationOutOfBoundsFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.OUT_OF_BOUNDS,
                List.of("e2 e9"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.OUT_OF_BOUNDS,
                List.of("e9 e2"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.OUT_OF_BOUNDS,
                List.of("e9 e9"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.OUT_OF_BOUNDS,
                List.of("1 1 10 10"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.OUT_OF_BOUNDS,
                List.of("10 10 1 1"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.OUT_OF_BOUNDS,
                List.of("-10 -10 10 10"));
    }

    @Test
    public void basicMoveValidationFromEqualsToFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.FROM_EQUALS_TO,
                List.of("e2 e2"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.FROM_EQUALS_TO,
                List.of("1 3 1 3"));
    }

    @Test
    public void basicMoveValidationChessPieceFromIsNullFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.CHESS_PIECE_FROM_IS_NULL,
                List.of("e4 e2"));
    }

    @Test
    public void basicMoveValidationEatYourChessPieceFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.EAT_YOUR_CHESS_PIECE,
                List.of(
                        "e2 e3",
                        "e7 e6",
                        "f2 e3"));
    }

    @Test
    public void basicMoveValidationNotYourTurnFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.NOT_YOUR_TURN,
                List.of("e7 e6"));
    }
}