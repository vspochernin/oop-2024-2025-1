package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color);
    }

    public Queen(Queen other) {
        super(other);
    }

    @Override
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessUtils.basicMoveValidation(board, from, to);

        try {
            ChessUtils.bishopMoveValidation(board, from, to);
            return; // Походили как слон.
        } catch (IllegalMoveException e) {
            // Попробовали пойти по диагонали, но между были фигуры.
            if (e.getReason().equals(IllegalMoveReason.CHESS_PIECES_BETWEEN)) {
                throw e;
            }
        }

        try {
            ChessUtils.rookMoveValidation(board, from, to);
            return; // Походили как ладья.
        } catch (IllegalMoveException e) {
            // Попробовали пойти по вертикали или горизонтали, но между были фигуры.
            if (e.getReason().equals(IllegalMoveReason.CHESS_PIECES_BETWEEN)) {
                throw e;
            }
        }

        throw new IllegalMoveException(IllegalMoveReason.QUEEN_ILLEGAL_MOVE);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public ChessPiece copy() {
        return new Queen(this);
    }
}
