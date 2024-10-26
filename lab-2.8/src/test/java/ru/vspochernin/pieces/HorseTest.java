package ru.vspochernin.pieces;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.utils.TestUtils;

/**
 * @author pochernin-vla
 */
class HorseTest extends TestWithBoard {

    @Test
    public void successfulHorseMove() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of(
                        "b1 a3",
                        "b8 a6",
                        "a3 c4",
                        "a6 c5",
                        "g1 h3",
                        "g8 h6",
                        "h3 f4",
                        "h6 f5"));
    }

    @Test
    public void successfulHorseEats() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of(
                        "b1 a3",
                        "b8 a6",
                        "a3 b5",
                        "a6 b4",
                        "b5 a7",
                        "b4 a2"));
    }

    @Test
    public void horseCrazyMoveFail() {
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.HORSE_ILLEGAL_MOVE,
                List.of("b1 f5"));
        TestUtils.acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
                board,
                IllegalMoveReason.HORSE_ILLEGAL_MOVE,
                List.of("e2 e4",
                        "b8 f4"));
    }
}