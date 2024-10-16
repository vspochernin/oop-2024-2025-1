package ru.vspochernin;

import java.util.Optional;

import ru.vspochernin.model.Color;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.Bishop;
import ru.vspochernin.pieces.ChessPiece;
import ru.vspochernin.pieces.Horse;
import ru.vspochernin.pieces.King;
import ru.vspochernin.pieces.Pawn;
import ru.vspochernin.pieces.Queen;
import ru.vspochernin.pieces.Rook;
import ru.vspochernin.utils.ChessUtils;

public final class ChessBoard {

    private final ChessPiece[][] board = new ChessPiece[8][8];
    private Color nowPlayerColor = Color.WHITE;

    public ChessBoard() {
        board[0][0] = new Rook(Color.WHITE);
        board[0][1] = new Horse(Color.WHITE);
        board[0][2] = new Bishop(Color.WHITE);
        board[0][3] = new Queen(Color.WHITE);
        board[0][4] = new King(Color.WHITE);
        board[0][5] = new Bishop(Color.WHITE);
        board[0][6] = new Horse(Color.WHITE);
        board[0][7] = new Rook(Color.WHITE);
        board[1][0] = new Pawn(Color.WHITE);
        board[1][1] = new Pawn(Color.WHITE);
        board[1][2] = new Pawn(Color.WHITE);
        board[1][3] = new Pawn(Color.WHITE);
        board[1][4] = new Pawn(Color.WHITE);
        board[1][5] = new Pawn(Color.WHITE);
        board[1][6] = new Pawn(Color.WHITE);
        board[1][7] = new Pawn(Color.WHITE);

        board[7][0] = new Rook(Color.BLACK);
        board[7][1] = new Horse(Color.BLACK);
        board[7][2] = new Bishop(Color.BLACK);
        board[7][3] = new Queen(Color.BLACK);
        board[7][4] = new King(Color.BLACK);
        board[7][5] = new Bishop(Color.BLACK);
        board[7][6] = new Horse(Color.BLACK);
        board[7][7] = new Rook(Color.BLACK);
        board[6][0] = new Pawn(Color.BLACK);
        board[6][1] = new Pawn(Color.BLACK);
        board[6][2] = new Pawn(Color.BLACK);
        board[6][3] = new Pawn(Color.BLACK);
        board[6][4] = new Pawn(Color.BLACK);
        board[6][5] = new Pawn(Color.BLACK);
        board[6][6] = new Pawn(Color.BLACK);
        board[6][7] = new Pawn(Color.BLACK);
    }

    public boolean moveToPosition(Position from, Position to) {
        if (!ChessUtils.basicMoveValidation(this, from, to)) {
            return false;
        }

        if (!nowPlayerColor.equals(getPlayerColorAtPosition(from))) {
            return false;
        }

        if (!getChessPieceAtPosition(from).canMove(this, from, to)) {
            return false;
        }

        setChessPieceAtPosition(getChessPieceAtPosition(from), to);
        setChessPieceAtPosition(null, from);
        nowPlayerColor = nowPlayerColor.swap();

        return true;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayerColor);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        System.out.println("-------------------------------------");

        for (int i = 7; i >= 0; i--) {
            System.out.print(i + "|\t");
            for (int j = 0; j <= 7; j++) {
                Position position = new Position(i, j);
                ChessPiece chessPiece = getChessPieceAtPosition(position);
                if (chessPiece == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(chessPiece.getSymbol() + chessPiece.getColorSymbol() + "\t");
                }
            }
            System.out.print("|" + i + "\t");
            System.out.println();
        }

        System.out.println("-------------------------------------");
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        System.out.println();
        System.out.println("Player 1(White)");
    }

    public ChessPiece getChessPieceAtPosition(Position position) {
        return board[position.line()][position.column()];
    }

    public Color getPlayerColorAtPosition(Position position) {
        return Optional.ofNullable(getChessPieceAtPosition(position))
                .map(ChessPiece::getColor)
                .orElse(null);
    }

    private void setChessPieceAtPosition(ChessPiece chessPiece, Position position) {
        board[position.line()][position.column()] = chessPiece;
    }
}
