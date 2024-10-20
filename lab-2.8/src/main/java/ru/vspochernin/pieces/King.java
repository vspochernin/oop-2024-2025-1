package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class King extends ChessPiece {

    public King(Color color) {
        super(color);
    }

    public King(King other) {
        super(other);
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        if (ChessUtils.failBasicMoveValidation(board, from, to)) {
            return false;
        }

        Position diff = to.diff(from);
        if (Math.abs(diff.line()) > 1 || Math.abs(diff.column()) > 1) {
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public ChessPiece copy() {
        return new King(this);
    }
}
