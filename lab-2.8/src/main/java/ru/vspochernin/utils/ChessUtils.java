package ru.vspochernin.utils;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.ChessPiece;

public final class ChessUtils {

    private ChessUtils() {
    }

    public static boolean basicMoveValidation(ChessBoard board, Position from, Position to) {
        if (from.isOutOfBounds() || to.isOutOfBounds()) {
            return false;
        }

        if (from.equals(to)) {
            return false;
        }

        ChessPiece chessPieceFrom = board.getChessPieceAtPosition(from);
        if (chessPieceFrom == null) {
            return false;
        }
        Color chessPieceFromColor = chessPieceFrom.getColor();

        if (chessPieceFromColor.equals(board.getPlayerColorAtPosition(to))) {
            return false;
        }

        if (!chessPieceFromColor.equals(board.getNowPlayerColor())) {
            return false;
        }

        return true;
    }
}
