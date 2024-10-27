package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

class PawnTest extends TestWithBoard {

    @Test
    public void successfulPawnOneCellMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("e2 e3",
                        "d7 d6"));
    }

    @Test
    public void successfulPawnTwoCellMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("e2 e4",
                        "d7 d5"));
    }

    @Test
    public void blockedPawnOneCellMoveFail() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("e2 e4",
                        "d7 d5",
                        "e4 e5",
                        "d5 d4",
                        "e5 e6",
                        "d4 d3"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("d2 d3"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("a2 a3",
                        "e7 e6"));
    }

    @Test
    public void blockedPawnTwoCellMoveFail() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("e2 e4",
                        "d7 d5",
                        "e4 e5",
                        "d5 d4",
                        "e5 e6",
                        "d4 d3"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("d2 d4"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("a2 a3",
                        "e7 e5"));
    }

    @Test
    public void successfulPawnEats() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("c2 c4",
                        "c7 c5",
                        "d2 d4",
                        "d7 d5",
                        "e2 e4",
                        "e7 e5",
                        "f2 f4",
                        "f7 f5",
                        "c4 d5",
                        "c5 d4",
                        "f4 e5",
                        "f5 e4"));
    }

    @Test
    public void pawnCrazyMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("e2 a5"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("e2 e4",
                        "e7 a5"));
    }
}