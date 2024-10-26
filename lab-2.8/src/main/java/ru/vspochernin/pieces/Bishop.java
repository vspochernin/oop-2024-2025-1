package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public final class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color);
    }

    public Bishop(Bishop other) {
        super(other);
    }

    public static void bishopMoveValidation(ChessBoard board, Position from, Position to) {
        Position diff = to.minus(from);
        if (Math.abs(diff.line()) != Math.abs(diff.column())) {
            throw new IllegalMoveException(IllegalMoveReason.BISHOP_ILLEGAL_MOVE);
        }

        int lineStep = diff.line() > 0 ? 1 : -1;
        int columnStep = diff.column() > 0 ? 1 : -1;

        Position pos;
        for (pos = from.relative(lineStep, columnStep); !pos.equals(to); pos = pos.relative(lineStep, columnStep)) {
            if (board.getChessPieceAtPosition(pos) != null) {
                throw new IllegalMoveException(IllegalMoveReason.CHESS_PIECES_BETWEEN);
            }
        }
    }

    @Override
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessBoard.basicMoveValidation(board, from, to);
        bishopMoveValidation(board, from, to);
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
