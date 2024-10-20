package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color);
    }

    public Rook(Rook other) {
        super(other);
    }

    @Override
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessUtils.basicMoveValidation(board, from, to);
        ChessUtils.rookMoveValidation(board, from, to);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public ChessPiece copy() {
        return new Rook(this);
    }
}
