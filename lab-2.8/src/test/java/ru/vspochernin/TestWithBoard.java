package ru.vspochernin;

import org.junit.jupiter.api.BeforeEach;
import ru.vspochernin.board.ChessBoard;

/**
 * @author pochernin-vla
 */
public abstract class TestWithBoard {

    protected ChessBoard board;

    @BeforeEach
    public void setUp() {
        board = new ChessBoard();
    }
}
