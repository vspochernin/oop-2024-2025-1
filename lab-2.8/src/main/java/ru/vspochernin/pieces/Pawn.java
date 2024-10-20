package ru.vspochernin.pieces;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color);
    }

    public Pawn(Pawn other) {
        super(other);
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        if (ChessUtils.failBasicMoveValidation(board, from, to)) {
            return false;
        }

        if (color.equals(Color.WHITE)) {
            if (to.equals(from.relative(1, 0))
                    && board.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if (to.equals(from.relative(2, 0))
                    && isUntouched()
                    && board.getChessPieceAtPosition(from.relative(1, 0)) == null
                    && board.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if ((to.equals(from.relative(1, 1)) || to.equals(from.relative(1, -1)))
                    && board.getChessPieceAtPosition(to) != null
                    && !board.getPlayerColorAtPosition(to).equals(board.getNowPlayerColor()))
            {
                return true;
            }
        }

        if (color.equals(Color.BLACK)) {
            if (to.equals(from.relative(-1, 0))
                    && board.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if (to.equals(from.relative(-2, 0))
                    && isUntouched()
                    && board.getChessPieceAtPosition(from.relative(-1, 0)) == null
                    && board.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if ((to.equals(from.relative(-1, 1)) || to.equals(from.relative(-1, -1)))
                    && board.getChessPieceAtPosition(to) != null
                    && !board.getPlayerColorAtPosition(to).equals(board.getNowPlayerColor()))
            {
                return true;
            }
        }

        return false;
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
