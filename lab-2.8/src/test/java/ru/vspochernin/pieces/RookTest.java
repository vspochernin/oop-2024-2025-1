package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

/**
 * @author pochernin-vla
 */
class RookTest extends TestWithBoard {

    @Test
    public void successfulRookMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("a2 a4",
                        "a7 a5",
                        "a1 a3",
                        "a8 a6",
                        "a3 h3",
                        "a6 h6",
                        "h3 g3",
                        "h6 g6"));
    }

    @Test
    public void successfulRookEats() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("a2 a4",
                        "a7 a5",
                        "a1 a3",
                        "a8 a6",
                        "a3 h3",
                        "a6 h6",
                        "h3 g3",
                        "h6 h2",
                        "g3 g7"));
    }

    @Test
    public void rookCrazyMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.ROOK_ILLEGAL_MOVE,
                List.of("a2 a4",
                        "a7 a5",
                        "a1 a3",
                        "a8 a6",
                        "a3 d6"));
    }

    @Test
    public void rookChessPiecesBetweenMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.CHESS_PIECES_BETWEEN,
                List.of("a1 a5"));
    }
}