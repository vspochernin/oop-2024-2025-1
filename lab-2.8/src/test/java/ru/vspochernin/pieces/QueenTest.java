package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

/**
 * @author pochernin-vla
 */
class QueenTest extends TestWithBoard {

    @Test
    public void successfulQueenMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("c2 c3",
                        "c7 c6",
                        "d1 a4",
                        "d8 a5",
                        "a4 h4",
                        "a5 h5",
                        "h4 h3",
                        "h5 h6",
                        "h3 e6",
                        "h6 e3",
                        "e6 f6",
                        "e3 f3",
                        "f6 e5",
                        "f3 e4"));
    }

    @Test
    public void successfulQueenEats() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("c2 c3",
                        "c7 c6",
                        "d1 a4",
                        "d8 a5",
                        "a4 h4",
                        "a5 g5",
                        "h4 h7",
                        "g5 g2"));
    }

    @Test
    public void queenCrazyMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.QUEEN_ILLEGAL_MOVE,
                List.of("c2 c3",
                        "c7 c6",
                        "d1 a4",
                        "d8 a5",
                        "a4 d8"));
    }

    @Test
    public void queenChessPiecesBetweenMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.CHESS_PIECES_BETWEEN,
                List.of("d1 g4"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.CHESS_PIECES_BETWEEN,
                List.of("d1 d4"));
    }
}