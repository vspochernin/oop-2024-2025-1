package ru.vspochernin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.MoveParser;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    private ChessBoard board;

    @BeforeEach
    public void setUp() {
        board = new ChessBoard();
    }

    @Test
    public void testPawnInitialShortMove() {
        assertDoesNotThrow(() -> board.moveToPosition(MoveParser.parseMove("a2 a3")));
    }

    @Test
    public void testPawnInitialLongMove() {
        assertDoesNotThrow(() -> board.moveToPosition(MoveParser.parseMove("a2 a4")));
    }

    @Test
    public void testPawnInitialIllegalMoves() {
        IllegalMoveException e1 =
                assertThrows(IllegalMoveException.class, () -> board.moveToPosition(MoveParser.parseMove("a2 a2")));
        assertEquals(e1.getReason(), IllegalMoveReason.FROM_EQUALS_TO);

        IllegalMoveException e2 =
                assertThrows(IllegalMoveException.class, () -> board.moveToPosition(MoveParser.parseMove("a2 a5")));
        assertEquals(e2.getReason(), IllegalMoveReason.PAWN_ILLEGAL_MOVE);
    }
}