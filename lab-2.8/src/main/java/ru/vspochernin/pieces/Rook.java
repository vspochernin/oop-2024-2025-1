package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public final class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        throw new UnsupportedOperationException("TODO: implement"); // TODO: implement.
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
