package ru.vspochernin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.utils.MoveParser;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    private ChessBoard board;

    @BeforeEach
    public void setUp() {
        board = new ChessBoard();
    }

    @Test
    public void testPawnInitialMove() {
        assertTrue(board.moveToPosition(MoveParser.parseMove("a2 a3")));
    }

    @Test
    public void testPawnInitialLongMove() {
        assertTrue(board.moveToPosition(MoveParser.parseMove("a2 a4")));
    }

    @Test
    public void testPawnIllegalMoves() {
        assertFalse(board.moveToPosition(MoveParser.parseMove("a2 a2")));
        assertFalse(board.moveToPosition(MoveParser.parseMove("a2 a5")));
    }
}