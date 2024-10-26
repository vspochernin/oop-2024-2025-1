package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public final class Horse extends ChessPiece {

    public Horse(Color color) {
        super(color);
    }

    public Horse(Horse other) {
        super(other);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessBoard.basicMoveValidation(board, from, to);

        if ((to.equals(from.relative(+2, +1))
                || to.equals(from.relative(+2, -1))
                || to.equals(from.relative(-2, +1))
                || to.equals(from.relative(-2, -1))
                || to.equals(from.relative(+1, +2))
                || to.equals(from.relative(+1, -2))
                || to.equals(from.relative(-1, +2))
                || to.equals(from.relative(-1, -2))))
        {
            return;
        }

        throw new IllegalMoveException(IllegalMoveReason.HORSE_ILLEGAL_MOVE);
    }

    @Override
    public ChessPiece copy() {
        return new Horse(this);
    }
}
