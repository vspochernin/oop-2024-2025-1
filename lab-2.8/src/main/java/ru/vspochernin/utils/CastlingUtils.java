package ru.vspochernin.utils;

import java.util.List;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.ChessPiece;
import ru.vspochernin.pieces.King;
import ru.vspochernin.pieces.Rook;

/**
 * @author pochernin-vla
 */
public class CastlingUtils {

    public static final Position CASTLING0_WHITE_ROOK_FROM = new Position(0, 0);
    public static final Position CASTLING0_WHITE_ROOK_TO = new Position(0, 3);
    public static final Position CASTLING0_WHITE_KING_FROM = new Position(0, 4);
    public static final Position CASTLING0_WHITE_KING_TO = new Position(0, 2);
    public static final List<Position> CASTLING0_WHITE_POSITIONS_BETWEEN = List.of(
            new Position(0, 1),
            CASTLING0_WHITE_KING_TO,
            CASTLING0_WHITE_ROOK_TO);
    public static final Position CASTLING7_WHITE_ROOK_FROM = new Position(0, 7);
    public static final Position CASTLING7_WHITE_ROOK_TO = new Position(0, 5);
    public static final Position CASTLING7_WHITE_KING_FROM = new Position(0, 4);
    public static final Position CASTLING7_WHITE_KING_TO = new Position(0, 6);
    public static final List<Position> CASTLING7_WHITE_POSITIONS_BETWEEN = List.of(
            CASTLING7_WHITE_KING_TO,
            CASTLING7_WHITE_ROOK_TO);

    public static final Position CASTLING0_BLACK_ROOK_FROM = new Position(7, 0);
    public static final Position CASTLING0_BLACK_ROOK_TO = new Position(7, 3);
    public static final Position CASTLING0_BLACK_KING_FROM = new Position(7, 4);
    public static final Position CASTLING0_BLACK_KING_TO = new Position(7, 2);
    public static final List<Position> CASTLING0_BLACK_POSITIONS_BETWEEN = List.of(
            new Position(7, 1),
            CASTLING0_BLACK_KING_TO,
            CASTLING0_BLACK_ROOK_TO);
    public static final Position CASTLING7_BLACK_ROOK_FROM = new Position(7, 7);
    public static final Position CASTLING7_BLACK_ROOK_TO = new Position(7, 5);
    public static final Position CASTLING7_BLACK_KING_FROM = new Position(7, 4);
    public static final Position CASTLING7_BLACK_KING_TO = new Position(7, 6);
    public static final List<Position> CASTLING7_BLACK_POSITIONS_BETWEEN = List.of(
            CASTLING7_BLACK_KING_TO,
            CASTLING7_BLACK_ROOK_TO);

    private CastlingUtils() {
    }

    public static void castling0Validation(ChessBoard board) {
        if (board.getNowPlayerColor().equals(Color.WHITE)) {
            castlingValidation(
                    board,
                    CASTLING0_WHITE_ROOK_FROM,
                    CASTLING0_WHITE_KING_FROM,
                    CASTLING0_WHITE_POSITIONS_BETWEEN);
        }

        if (board.getNowPlayerColor().equals(Color.BLACK)) {
            castlingValidation(
                    board,
                    CASTLING0_BLACK_ROOK_FROM,
                    CASTLING0_BLACK_KING_FROM,
                    CASTLING0_BLACK_POSITIONS_BETWEEN);
        }

    }

    public static void castling7Validation(ChessBoard board) {
        if (board.getNowPlayerColor().equals(Color.WHITE)) {
            castlingValidation(
                    board,
                    CASTLING7_WHITE_ROOK_FROM,
                    CASTLING7_WHITE_KING_FROM,
                    CASTLING7_WHITE_POSITIONS_BETWEEN);
        }

        if (board.getNowPlayerColor().equals(Color.BLACK)) {
            castlingValidation(
                    board,
                    CASTLING7_BLACK_ROOK_FROM,
                    CASTLING7_BLACK_KING_FROM,
                    CASTLING7_BLACK_POSITIONS_BETWEEN);
        }
    }

    private static void castlingValidation(
            ChessBoard board,
            Position rookPositionFrom,
            Position kingPositionFrom,
            List<Position> positionsBetween)
    {
        ChessPiece rookChessPieceFrom = board.getChessPieceAtPosition(rookPositionFrom);
        ChessPiece kingChessPieceFrom = board.getChessPieceAtPosition(kingPositionFrom);

        if (!(rookChessPieceFrom instanceof Rook) || !(kingChessPieceFrom instanceof King)) {
            throw new IllegalMoveException(IllegalMoveReason.CASTLING_NO_ROOK_OR_KING);
        }

        if (!rookChessPieceFrom.isUntouched() || !kingChessPieceFrom.isUntouched()) {
            throw new IllegalMoveException(IllegalMoveReason.CASTLING_TOUCHED);
        }

        for (Position positionBetween : positionsBetween) {
            if (board.getChessPieceAtPosition(positionBetween) != null) {
                throw new IllegalMoveException(IllegalMoveReason.CHESS_PIECES_BETWEEN);
            }
        }
    }
}
