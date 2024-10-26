package ru.vspochernin.utils;

import java.util.List;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Move;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author pochernin-vla
 */
public class TestUtils {

    public static void acceptMovesToTheBoardWithoutExceptions(ChessBoard board, List<String> moveStrs) {
        for (String moveString : moveStrs) {
            assertDoesNotThrow(() -> board.moveToPosition(Move.of(moveString)));
        }
    }

    public static void acceptMovesToTheBoardWithIllegalMoveReasonAtTheEnd(
            ChessBoard board,
            IllegalMoveReason illegalMoveReason,
            List<String> moveStrs)
    {
        if (moveStrs.isEmpty()) {
            return;
        }

        if (moveStrs.size() == 1) {
            IllegalMoveException thrown =
                    assertThrows(IllegalMoveException.class, () -> board.moveToPosition(Move.of(moveStrs.getFirst())));
            assertEquals(illegalMoveReason, thrown.getReason());
            return;
        }

        for (String move : moveStrs.subList(0, moveStrs.size() - 1)) {
            assertDoesNotThrow(() -> board.moveToPosition(Move.of(move)));
        }
        IllegalMoveException thrown =
                assertThrows(IllegalMoveException.class, () -> board.moveToPosition(Move.of(moveStrs.getLast())));
        assertEquals(illegalMoveReason, thrown.getReason());
    }
}
