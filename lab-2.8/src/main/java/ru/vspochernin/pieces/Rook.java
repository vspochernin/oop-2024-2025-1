package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public final class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color);
    }

    public Rook(Rook other) {
        super(other);
    }

    public static void rookMoveValidation(ChessBoard board, Position from, Position to) {
        Position diff = to.minus(from);
        if (Math.abs(diff.line()) > 0 == Math.abs(diff.column()) > 0) {
            throw new IllegalMoveException(IllegalMoveReason.ROOK_ILLEGAL_MOVE);
        }

        int lineStep = diff.line() == 0 ? 0 : (diff.line() > 0 ? 1 : -1);
        int columnStep = diff.column() == 0 ? 0 : (diff.column() > 0 ? 1 : -1);

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
        rookMoveValidation(board, from, to);
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
