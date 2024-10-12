package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;

public abstract class ChessPiece {

    private final String color;
    private boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
