package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
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
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessUtils.basicMoveValidation(board, from, to);

        Position diff = to.minus(from);
        if (Math.abs(diff.line()) > 1 || Math.abs(diff.column()) > 1) {
            throw new IllegalMoveException(IllegalMoveReason.KING_ILLEGAL_MOVE);
        }
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
