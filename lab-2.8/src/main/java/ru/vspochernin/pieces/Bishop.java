package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color);
    }

    public Bishop(Bishop other) {
        super(other);
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        if (ChessUtils.failBasicMoveValidation(board, from, to)) {
            return false;
        }

        if (ChessUtils.failBishopMoveValidation(board, from, to)) {
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public ChessPiece copy() {
        return new Bishop(this);
    }
}
