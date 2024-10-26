package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

class PawnTest extends TestWithBoard {

    @Test
    public void testWhitePawnInitialShortMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(board, List.of("a2 a3"));
    }

    @Test
    public void testWhitePawnInitialLongMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(board, List.of("a2 a4"));
    }

    @Test
    public void testWhitePawnInitialIllegalMoves() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.FROM_EQUALS_TO,
                List.of("a2 a2"));


        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.PAWN_ILLEGAL_MOVE,
                List.of("a2 a5"));
    }

    @Test
    public void testWhitePawnAttacks() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of(
                        "a2 a4",
                        "b7 b5",
                        "a4 b5"));
    }

    @Test
    public void testBlackPawnInitialShortMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of(
                        "a2 a4",
                        "a7 a6"));
    }
}