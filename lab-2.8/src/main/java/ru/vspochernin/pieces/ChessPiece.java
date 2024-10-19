package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public abstract class ChessPiece {

    private final Color color;
    private boolean isUntouched = true;

    public ChessPiece(Color color) {
        this.color = color;
    }

    public final Color getColor() {
        return color;
    }

    public final String getColorSymbol() {
        return color.getValue().substring(0, 1).toLowerCase();
    }

    public final boolean getIsUntouched() {
        return isUntouched;
    }

    public final void setIsUntouchedFalse() {
        isUntouched = false;
    }

    public abstract boolean canMove(ChessBoard chessBoard, Position from, Position to);

    public abstract String getSymbol();
}
