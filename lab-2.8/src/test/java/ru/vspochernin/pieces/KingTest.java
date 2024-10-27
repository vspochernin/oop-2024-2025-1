package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

class KingTest extends TestWithBoard {

    @Test
    public void successfulKingMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("f2 f3",
                        "f7 f6",
                        "e1 f2",
                        "e8 f7",
                        "f2 e3",
                        "f7 e6",
                        "e3 d3",
                        "e6 d6",
                        "d3 d4",
                        "d6 e6",
                        "d4 d3",
                        "e6 e5"));
    }

    @Test
    public void successfulKingEats() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("e2 e4",
                        "e7 e5",
                        "f2 f4",
                        "f7 f5",
                        "f4 e5",
                        "f5 e4",
                        "e5 e6",
                        "e4 e3",
                        "e6 e7",
                        "e8 e7",
                        "a2 a3",
                        "e3 e2",
                        "e1 e2"));
    }

    @Test
    public void kingCrazyMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.KING_ILLEGAL_MOVE,
                List.of("e1 a4"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.KING_ILLEGAL_MOVE,
                List.of("e2 e4",
                        "e8 a5"));
    }

    @Test
    public void kingUnderAttackMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.KING_UNDER_ATTACK,
                List.of("g2 g4",
                        "e7 e5",
                        "f2 f3",
                        "d8 h4",
                        "e2 e3"));
    }
}