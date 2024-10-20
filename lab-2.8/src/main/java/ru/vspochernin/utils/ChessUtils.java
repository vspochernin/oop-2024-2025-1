package ru.vspochernin.utils;

import java.util.List;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.ChessPiece;
import ru.vspochernin.pieces.King;
import ru.vspochernin.pieces.Rook;

public final class ChessUtils {

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

    private ChessUtils() {
    }

    public static boolean failBasicMoveValidation(ChessBoard board, Position from, Position to) {
        if (from.isOutOfBounds() || to.isOutOfBounds()) {
            return true;
        }

        if (from.equals(to)) {
            return true;
        }

        ChessPiece chessPieceFrom = board.getChessPieceAtPosition(from);
        if (chessPieceFrom == null) {
            return true;
        }
        Color chessPieceFromColor = chessPieceFrom.getColor();

        if (chessPieceFromColor.equals(board.getPlayerColorAtPosition(to))) {
            return true;
        }

        if (!chessPieceFromColor.equals(board.getNowPlayerColor())) {
            return true;
        }

        return false;
    }

    public static boolean failBishopMoveValidation(ChessBoard board, Position from, Position to) {
        Position diff = to.diff(from);
        if (Math.abs(diff.line()) != Math.abs(diff.column())) {
            return true;
        }

        int lineStep = diff.line() > 0 ? 1 : -1;
        int columnStep = diff.column() > 0 ? 1 : -1;

        Position pos;
        for (pos = from.relative(lineStep, columnStep); !pos.equals(to); pos = pos.relative(lineStep, columnStep)) {
            if (board.getChessPieceAtPosition(pos) != null) {
                return true;
            }
        }

        return false;
    }

    public static boolean failRookMoveValidation(ChessBoard board, Position from, Position to) {
        Position diff = to.diff(from);
        if (Math.abs(diff.line()) > 0 == Math.abs(diff.column()) > 0) {
            return true;
        }

        int lineStep = diff.line() == 0 ? 0 : (diff.line() > 0 ? 1 : -1);
        int columnStep = diff.column() == 0 ? 0 : (diff.column() > 0 ? 1 : -1);

        Position pos;
        for (pos = from.relative(lineStep, columnStep); !pos.equals(to); pos = pos.relative(lineStep, columnStep)) {
            if (board.getChessPieceAtPosition(pos) != null) {
                return true;
            }
        }

        return false;
    }

    public static boolean failCastling0Validation(ChessBoard board) {
        if (board.getNowPlayerColor().equals(Color.WHITE)) {
            return failCastlingValidation(
                    board,
                    CASTLING0_WHITE_ROOK_FROM,
                    CASTLING0_WHITE_KING_FROM,
                    CASTLING0_WHITE_POSITIONS_BETWEEN);
        }

        if (board.getNowPlayerColor().equals(Color.BLACK)) {
            return failCastlingValidation(
                    board,
                    CASTLING0_BLACK_ROOK_FROM,
                    CASTLING0_BLACK_KING_FROM,
                    CASTLING0_BLACK_POSITIONS_BETWEEN);
        }

        return false;
    }

    public static boolean failCastling7Validation(ChessBoard board) {
        if (board.getNowPlayerColor().equals(Color.WHITE)) {
            return failCastlingValidation(
                    board,
                    CASTLING7_WHITE_ROOK_FROM,
                    CASTLING7_WHITE_KING_FROM,
                    CASTLING7_WHITE_POSITIONS_BETWEEN);
        }

        if (board.getNowPlayerColor().equals(Color.BLACK)) {
            return failCastlingValidation(
                    board,
                    CASTLING7_BLACK_ROOK_FROM,
                    CASTLING7_BLACK_KING_FROM,
                    CASTLING7_BLACK_POSITIONS_BETWEEN);
        }

        return false;
    }

    private static boolean failCastlingValidation(
            ChessBoard board,
            Position rookPositionFrom,
            Position kingPositionFrom,
            List<Position> positionsBetween)
    {
        ChessPiece rookChessPieceFrom = board.getChessPieceAtPosition(rookPositionFrom);
        ChessPiece kingChessPieceFrom = board.getChessPieceAtPosition(kingPositionFrom);

        if (!(rookChessPieceFrom instanceof Rook) || !(kingChessPieceFrom instanceof King)) {
            return true;
        }

        if (!rookChessPieceFrom.isUntouched() || !kingChessPieceFrom.isUntouched()) {
            return true;
        }

        for (Position positionBetween : positionsBetween) {
            if (board.getChessPieceAtPosition(positionBetween) != null) {
                return true;
            }
        }

        return false;
    }
}
