package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public abstract class ChessPiece {

    protected final Color color;
    private boolean isUntouched = true;

    public ChessPiece(Color color) {
        this.color = color;
    }

    public ChessPiece(ChessPiece other) {
        this.color = other.color;
        this.isUntouched = other.isUntouched;
    }

    public final Color getColor() {
        return color;
    }

    public final String getColorSymbol() {
        return color.getValue().substring(0, 1).toLowerCase();
    }

    public final boolean isUntouched() {
        return isUntouched;
    }

    public final void setUntouchedFalse() {
        isUntouched = false;
    }

    public abstract void validateMove(ChessBoard board, Position from, Position to);

    public abstract String getSymbol();

    public abstract ChessPiece copy();
}
