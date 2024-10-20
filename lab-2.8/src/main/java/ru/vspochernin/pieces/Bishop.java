package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
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
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessUtils.basicMoveValidation(board, from, to);
        ChessUtils.bishopMoveValidation(board, from, to);
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
