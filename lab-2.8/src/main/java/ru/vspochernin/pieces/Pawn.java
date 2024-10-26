package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;

public final class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color);
    }

    public Pawn(Pawn other) {
        super(other);
    }

    @Override
    public void validateMove(ChessBoard board, Position from, Position to) {
        ChessBoard.basicMoveValidation(board, from, to);

        if (color.equals(Color.WHITE)) {
            if (to.equals(from.relative(1, 0))
                    && board.getChessPieceAtPosition(to) == null)
            {
                return;
            }

            if (to.equals(from.relative(2, 0))
                    && isUntouched()
                    && board.getChessPieceAtPosition(from.relative(1, 0)) == null
                    && board.getChessPieceAtPosition(to) == null)
            {
                return;
            }

            if ((to.equals(from.relative(1, 1)) || to.equals(from.relative(1, -1)))
                    && board.getChessPieceAtPosition(to) != null
                    && !board.getPlayerColorAtPosition(to).equals(board.getNowPlayerColor()))
            {
                return;
            }
        }

        if (color.equals(Color.BLACK)) {
            if (to.equals(from.relative(-1, 0))
                    && board.getChessPieceAtPosition(to) == null)
            {
                return;
            }

            if (to.equals(from.relative(-2, 0))
                    && isUntouched()
                    && board.getChessPieceAtPosition(from.relative(-1, 0)) == null
                    && board.getChessPieceAtPosition(to) == null)
            {
                return;
            }

            if ((to.equals(from.relative(-1, 1)) || to.equals(from.relative(-1, -1)))
                    && board.getChessPieceAtPosition(to) != null
                    && !board.getPlayerColorAtPosition(to).equals(board.getNowPlayerColor()))
            {
                return;
            }
        }

        throw new IllegalMoveException(IllegalMoveReason.PAWN_ILLEGAL_MOVE);
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public ChessPiece copy() {
        return new Pawn(this);
    }
}
