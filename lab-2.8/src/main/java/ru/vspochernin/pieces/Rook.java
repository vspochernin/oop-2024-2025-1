package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        if (ChessUtils.failBasicMoveValidation(board, from, to)) {
            return false;
        }

        if (ChessUtils.failRookMoveValidation(board, from, to)) {
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
