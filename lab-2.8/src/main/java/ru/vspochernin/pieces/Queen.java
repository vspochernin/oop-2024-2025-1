package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color);
    }

    public Queen(Queen other) {
        super(other);
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        if (ChessUtils.failBasicMoveValidation(board, from, to)) {
            return false;
        }

        if (ChessUtils.failBishopMoveValidation(board, from, to) &&
                ChessUtils.failRookMoveValidation(board, from, to))
        {
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public ChessPiece copy() {
        return new Queen(this);
    }
}
