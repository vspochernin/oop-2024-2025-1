package ru.vspochernin.utils;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.ChessPiece;

public final class ChessUtils {

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
}
