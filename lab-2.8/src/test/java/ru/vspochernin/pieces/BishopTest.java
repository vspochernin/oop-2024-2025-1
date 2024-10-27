package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

/**
 * @author pochernin-vla
 */
class BishopTest extends TestWithBoard {

    @Test
    public void successfulBishopMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b2 b3",
                        "b7 b6",
                        "c1 a3",
                        "c8 a6",
                        "a3 d6",
                        "a6 d3",
                        "d6 f4",
                        "d3 f5",
                        "f4 g5",
                        "f5 g4"));
    }

    @Test
    public void successfulBishopEats() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b2 b3",
                        "b7 b6",
                        "c1 a3",
                        "c8 a6",
                        "a3 e7",
                        "a6 e2"));
    }

    @Test
    public void bishopCrazyMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.BISHOP_ILLEGAL_MOVE,
                List.of("b2 b3",
                        "b7 b6",
                        "c1 a3",
                        "c8 a6",
                        "a3 g5"));
    }

    @Test
    public void bishopChessPiecesBetweenMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.CHESS_PIECES_BETWEEN,
                List.of("c1 f4"));
    }
}