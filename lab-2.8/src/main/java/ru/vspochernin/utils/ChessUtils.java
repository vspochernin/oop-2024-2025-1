package ru.vspochernin.utils;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.ChessPiece;

public final class ChessUtils {

    private ChessUtils() {
    }

    public static boolean basicMoveValidation(ChessBoard chessBoard, Position from, Position to) {
        if (from.isOutOfBounds() || to.isOutOfBounds()) {
            return false;
        }

        if (from.equals(to)) {
            return false;
        }

        ChessPiece chessPieceFrom = chessBoard.getChessPieceAtPosition(from);
        if (chessPieceFrom == null) {
            return false;
        }

        if (chessPieceFrom.getColor().equals(chessBoard.getPlayerColorAtPosition(to))) {
            return false;
        }

        return true;
    }
}
