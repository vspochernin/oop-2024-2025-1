package ru.vspochernin.pieces;

import ru.vspochernin.ChessBoard;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.utils.ChessUtils;

public final class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(ChessBoard chessBoard, Position from, Position to) {
        if (!ChessUtils.basicMoveValidation(chessBoard, from, to)) {
            return false;
        }

        if (getColor().equals(Color.WHITE)) {
            if (to.equals(from.relative(1, 0))
                    && chessBoard.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if (to.equals(from.relative(2, 0))
                    && isUntouched()
                    && chessBoard.getChessPieceAtPosition(from.relative(1, 0)) == null
                    && chessBoard.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if ((to.equals(from.relative(1, 1)) || to.equals(from.relative(1, -1)))
                    && chessBoard.getChessPieceAtPosition(to) != null
                    && !chessBoard.getPlayerColorAtPosition(to).equals(chessBoard.getNowPlayerColor()))
            {
                return true;
            }
        }

        if (getColor().equals(Color.BLACK)) {
            if (to.equals(from.relative(-1, 0))
                    && chessBoard.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if (to.equals(from.relative(-2, 0))
                    && isUntouched()
                    && chessBoard.getChessPieceAtPosition(from.relative(-1, 0)) == null
                    && chessBoard.getChessPieceAtPosition(to) == null)
            {
                return true;
            }

            if ((to.equals(from.relative(-1, 1)) || to.equals(from.relative(-1, -1)))
                    && chessBoard.getChessPieceAtPosition(to) != null
                    && !chessBoard.getPlayerColorAtPosition(to).equals(chessBoard.getNowPlayerColor()))
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
}
