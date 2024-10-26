package ru.vspochernin.utils;

import java.util.List;

import org.junit.jupiter.api.Test;
import ru.vspochernin.TestWithBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Move;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author pochernin-vla
 */
class CastlingUtilsTest extends TestWithBoard {

    @Test
    public void successfulCastling0() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b1 a3",
                        "b8 a6",
                        "d2 d3",
                        "d7 d6",
                        "c1 d2",
                        "c8 d7",
                        "e2 e3",
                        "e7 e6",
                        "d1 e2",
                        "d8 e7"));

        assertDoesNotThrow(() -> board.castling0());
        assertDoesNotThrow(() -> board.castling0());
    }

    @Test
    public void castling0NoRookFail() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b1 a3",
                        "b8 a6",
                        "d2 d3",
                        "d7 d6",
                        "c1 d2",
                        "c8 d7",
                        "e2 e3",
                        "e7 e6",
                        "d1 e2",
                        "d8 e7",
                        "a1 b1",
                        "a8 b8"));

        IllegalMoveException illegalMoveException =
                assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_NO_ROOK_OR_KING, illegalMoveException.getReason());

        assertDoesNotThrow(() -> board.moveToPosition(Move.of("h2 h3"))); // Передаем ход черным.

        illegalMoveException = assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_NO_ROOK_OR_KING, illegalMoveException.getReason());
        board.printBoard();
    }

    @Test
    public void castling0NoKingFail() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b1 a3",
                        "b8 a6",
                        "d2 d3",
                        "d7 d6",
                        "c1 d2",
                        "c8 d7",
                        "e2 e3",
                        "e7 e6",
                        "d1 e2",
                        "d8 e7",
                        "e1 d1",
                        "e8 d8"));

        IllegalMoveException illegalMoveException =
                assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_NO_ROOK_OR_KING, illegalMoveException.getReason());

        assertDoesNotThrow(() -> board.moveToPosition(Move.of("h2 h3"))); // Передаем ход черным.

        illegalMoveException = assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_NO_ROOK_OR_KING, illegalMoveException.getReason());
    }

    @Test
    public void castling0RookTouchedFail() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b1 a3",
                        "b8 a6",
                        "d2 d3",
                        "d7 d6",
                        "c1 d2",
                        "c8 d7",
                        "e2 e3",
                        "e7 e6",
                        "d1 e2",
                        "d8 e7",
                        "a1 b1",
                        "a8 b8",
                        "b1 a1",
                        "b8 a8"));
        IllegalMoveException illegalMoveException =
                assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_TOUCHED, illegalMoveException.getReason());

        assertDoesNotThrow(() -> board.moveToPosition(Move.of("h2 h3"))); // Передаем ход черным.

        illegalMoveException = assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_TOUCHED, illegalMoveException.getReason());
    }

    @Test
    public void castling0NoKingFail1() {
        TestUtils.acceptMovesToTheBoardWithoutExceptions(
                board,
                List.of("b1 a3",
                        "b8 a6",
                        "d2 d3",
                        "d7 d6",
                        "c1 d2",
                        "c8 d7",
                        "e2 e3",
                        "e7 e6",
                        "d1 e2",
                        "d8 e7",
                        "e1 d1",
                        "e8 d8",
                        "d1 e1",
                        "d8 e8"));
        IllegalMoveException illegalMoveException =
                assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_TOUCHED, illegalMoveException.getReason());

        assertDoesNotThrow(() -> board.moveToPosition(Move.of("h2 h3"))); // Передаем ход черным.

        illegalMoveException = assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CASTLING_TOUCHED, illegalMoveException.getReason());
    }

    @Test
    public void castling0ChessPiecesBetweenFail() {
        IllegalMoveException illegalMoveException =
                assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CHESS_PIECES_BETWEEN, illegalMoveException.getReason());

        assertDoesNotThrow(() -> board.moveToPosition(Move.of("h2 h3"))); // Передаем ход черным.

        illegalMoveException = assertThrows(IllegalMoveException.class, () -> board.castling0());
        assertEquals(IllegalMoveReason.CHESS_PIECES_BETWEEN, illegalMoveException.getReason());
    }
}