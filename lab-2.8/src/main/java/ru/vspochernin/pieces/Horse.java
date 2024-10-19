package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Horse extends ChessPiece {

    public Horse(Color color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        if (ChessUtils.failBasicMoveValidation(board, from, to)) {
            return false;
        }

        return (to.equals(from.relative(+2, +1))
                || to.equals(from.relative(+2, -1))
                || to.equals(from.relative(-2, +1))
                || to.equals(from.relative(-2, -1))
                || to.equals(from.relative(+1, +2))
                || to.equals(from.relative(+1, -2))
                || to.equals(from.relative(-1, +2))
                || to.equals(from.relative(-1, -2)));
    }
}
