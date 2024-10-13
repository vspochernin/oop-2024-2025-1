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
    public boolean canMove(ChessBoard chessBoard, Position from, Position to) {
        if (!ChessUtils.basicMoveValidation(chessBoard, from, to)) {
            return false;
        }

        return (to.line() == from.line() + 2 && to.column() == from.column() + 1)
                || (to.line() == from.line() + 2 && to.column() == from.column() - 1)
                || (to.line() == from.line() - 2 && to.column() == from.column() + 1)
                || (to.line() == from.line() - 2 && to.column() == from.column() - 1)
                || (to.line() == from.line() + 1 && to.column() == from.column() + 2)
                || (to.line() == from.line() + 1 && to.column() == from.column() - 2)
                || (to.line() == from.line() - 1 && to.column() == from.column() + 2)
                || (to.line() == from.line() - 1 && to.column() == from.column() - 2);
    }
}
