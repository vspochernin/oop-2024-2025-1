package ru.vspochernin.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Move;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest {

    private ChessBoard board;

    @BeforeEach
    public void setUp() {
        board = new ChessBoard();
    }

    @Test
    public void testWhitePawnInitialShortMove() {
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("a2 a3")));
    }

    @Test
    public void testWhitePawnInitialLongMove() {
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("a2 a4")));
    }

    @Test
    public void testWhitePawnInitialIllegalMoves() {
        IllegalMoveException e1 =
                assertThrows(IllegalMoveException.class, () -> board.moveToPosition(Move.of("a2 a2")));
        assertEquals(e1.getReason(), IllegalMoveReason.FROM_EQUALS_TO);

        IllegalMoveException e2 =
                assertThrows(IllegalMoveException.class, () -> board.moveToPosition(Move.of("a2 a5")));
        assertEquals(e2.getReason(), IllegalMoveReason.PAWN_ILLEGAL_MOVE);
    }

    @Test
    public void testWhitePawnAttacks() {
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("a2 a4")));
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("b7 b5")));
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("a4 b5")));
    }

    @Test
    public void testBlackPawnInitialShortMove() {
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("a2 a4")));
        assertDoesNotThrow(() -> board.moveToPosition(Move.of("a7 a6")));
    }
}