package ru.vspochernin.board;

import java.util.Optional;

import ru.vspochernin.exception.IllegalMoveException;
import ru.vspochernin.exception.IllegalMoveReason;
import ru.vspochernin.model.Color;
import ru.vspochernin.model.Move;
import ru.vspochernin.model.Position;
import ru.vspochernin.pieces.Bishop;
import ru.vspochernin.pieces.ChessPiece;
import ru.vspochernin.pieces.Horse;
import ru.vspochernin.pieces.King;
import ru.vspochernin.pieces.Pawn;
import ru.vspochernin.pieces.Queen;
import ru.vspochernin.pieces.Rook;
import ru.vspochernin.utils.CastlingUtils;

public final class ChessBoard {

    public static final int SIZE = 8;

    private final ChessPiece[][] board = new ChessPiece[SIZE][SIZE];
    private Color nowPlayerColor;

    public ChessBoard() {
        this.board[0][0] = new Rook(Color.WHITE);
        this.board[0][1] = new Horse(Color.WHITE);
        this.board[0][2] = new Bishop(Color.WHITE);
        this.board[0][3] = new Queen(Color.WHITE);
        this.board[0][4] = new King(Color.WHITE);
        this.board[0][5] = new Bishop(Color.WHITE);
        this.board[0][6] = new Horse(Color.WHITE);
        this.board[0][7] = new Rook(Color.WHITE);
        this.board[1][0] = new Pawn(Color.WHITE);
        this.board[1][1] = new Pawn(Color.WHITE);
        this.board[1][2] = new Pawn(Color.WHITE);
        this.board[1][3] = new Pawn(Color.WHITE);
        this.board[1][4] = new Pawn(Color.WHITE);
        this.board[1][5] = new Pawn(Color.WHITE);
        this.board[1][6] = new Pawn(Color.WHITE);
        this.board[1][7] = new Pawn(Color.WHITE);

        this.board[7][0] = new Rook(Color.BLACK);
        this.board[7][1] = new Horse(Color.BLACK);
        this.board[7][2] = new Bishop(Color.BLACK);
        this.board[7][3] = new Queen(Color.BLACK);
        this.board[7][4] = new King(Color.BLACK);
        this.board[7][5] = new Bishop(Color.BLACK);
        this.board[7][6] = new Horse(Color.BLACK);
        this.board[7][7] = new Rook(Color.BLACK);
        this.board[6][0] = new Pawn(Color.BLACK);
        this.board[6][1] = new Pawn(Color.BLACK);
        this.board[6][2] = new Pawn(Color.BLACK);
        this.board[6][3] = new Pawn(Color.BLACK);
        this.board[6][4] = new Pawn(Color.BLACK);
        this.board[6][5] = new Pawn(Color.BLACK);
        this.board[6][6] = new Pawn(Color.BLACK);
        this.board[6][7] = new Pawn(Color.BLACK);

        this.nowPlayerColor = Color.WHITE;
    }

    public ChessBoard(ChessBoard other) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = other.board[i][j] == null ? null : other.board[i][j].copy();
            }
        }
        this.nowPlayerColor = other.nowPlayerColor;
    }

    public static void basicMoveValidation(ChessBoard board, Position from, Position to) {
        if (from.isOutOfBounds() || to.isOutOfBounds()) {
            throw new IllegalMoveException(IllegalMoveReason.OUT_OF_BOUNDS);
        }

        if (from.equals(to)) {
            throw new IllegalMoveException(IllegalMoveReason.FROM_EQUALS_TO);
        }

        ChessPiece chessPieceFrom = board.getChessPieceAtPosition(from);
        if (chessPieceFrom == null) {
            throw new IllegalMoveException(IllegalMoveReason.CHESS_PIECE_FROM_IS_NULL);
        }
        Color chessPieceFromColor = chessPieceFrom.getColor();

        if (chessPieceFromColor.equals(board.getPlayerColorAtPosition(to))) {
            throw new IllegalMoveException(IllegalMoveReason.EAT_YOU_CHESS_PIECE);
        }

        if (!chessPieceFromColor.equals(board.getNowPlayerColor())) {
            throw new IllegalMoveException(IllegalMoveReason.NOT_YOUR_TURN);
        }
    }

    public Color getNowPlayerColor() {
        return nowPlayerColor;
    }

    public void moveToPosition(Move move) {
        Position from = move.from();
        Position to = move.to();

        basicMoveValidation(this, from, to);
        getChessPieceAtPosition(from).validateMove(this, from, to);

        ChessBoard boardCopy = new ChessBoard(this);
        boardCopy.doMoveToPositionAndSwapPlayer(from, to);
        if (boardCopy.isKingUnderAttackByColor(boardCopy.nowPlayerColor)) {
            throw new IllegalMoveException(IllegalMoveReason.KING_UNDER_ATTACK);
        }

        doMoveToPositionAndSwapPlayer(from, to);
    }

    public void castling0() {
        CastlingUtils.castling0Validation(this);

        Position rookPositionFrom = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING0_WHITE_ROOK_FROM
                : CastlingUtils.CASTLING0_BLACK_ROOK_FROM;
        Position kingPositionFrom = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING0_WHITE_KING_FROM
                : CastlingUtils.CASTLING0_BLACK_KING_FROM;
        Position rookPositionTo = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING0_WHITE_ROOK_TO
                : CastlingUtils.CASTLING0_BLACK_ROOK_TO;
        Position kingPositionTo = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING0_WHITE_KING_TO
                : CastlingUtils.CASTLING0_BLACK_KING_TO;

        ChessBoard boardCopy = new ChessBoard(this);
        boardCopy.doCastlingAndSwapPlayer(rookPositionFrom, kingPositionFrom, rookPositionTo, kingPositionTo);
        if (boardCopy.isKingUnderAttackByColor(boardCopy.nowPlayerColor)) {
            throw new IllegalMoveException(IllegalMoveReason.KING_UNDER_ATTACK);
        }

        doCastlingAndSwapPlayer(rookPositionFrom, kingPositionFrom, rookPositionTo, kingPositionTo);
    }

    public void castling7() {
        CastlingUtils.castling7Validation(this);

        Position rookPositionFrom = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING7_WHITE_ROOK_FROM
                : CastlingUtils.CASTLING7_BLACK_ROOK_FROM;
        Position kingPositionFrom = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING7_WHITE_KING_FROM
                : CastlingUtils.CASTLING7_BLACK_KING_FROM;
        Position rookPositionTo = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING7_WHITE_ROOK_TO
                : CastlingUtils.CASTLING7_BLACK_ROOK_TO;
        Position kingPositionTo = nowPlayerColor.equals(Color.WHITE)
                ? CastlingUtils.CASTLING7_WHITE_KING_TO
                : CastlingUtils.CASTLING7_BLACK_KING_TO;

        ChessBoard boardCopy = new ChessBoard(this);
        boardCopy.doCastlingAndSwapPlayer(rookPositionFrom, kingPositionFrom, rookPositionTo, kingPositionTo);
        if (boardCopy.isKingUnderAttackByColor(boardCopy.nowPlayerColor)) {
            throw new IllegalMoveException(IllegalMoveReason.KING_UNDER_ATTACK);
        }

        doCastlingAndSwapPlayer(rookPositionFrom, kingPositionFrom, rookPositionTo, kingPositionTo);
    }

    private void doMoveToPositionAndSwapPlayer(Position from, Position to) {
        ChessPiece chessPieceFrom = getChessPieceAtPosition(from);

        if (chessPieceFrom == null) {
            throw new IllegalStateException("Критическая ошибка во время хода");
        }

        chessPieceFrom.setUntouchedFalse();
        setChessPieceAtPosition(chessPieceFrom, to);
        setChessPieceAtPosition(null, from);
        nowPlayerColor = nowPlayerColor.opposite();
    }

    private void doCastlingAndSwapPlayer(
            Position rookPositionFrom,
            Position kingPositionFrom,
            Position rookPositionTo,
            Position kingPositionTo)
    {
        ChessPiece rookChessPieceFrom = getChessPieceAtPosition(rookPositionFrom);
        ChessPiece kingChessPieceFrom = getChessPieceAtPosition(kingPositionFrom);

        if (rookChessPieceFrom == null || kingChessPieceFrom == null) {
            throw new IllegalStateException("Критическая ошибка в во время рокировки");
        }

        rookChessPieceFrom.setUntouchedFalse();
        kingChessPieceFrom.setUntouchedFalse();
        setChessPieceAtPosition(rookChessPieceFrom, rookPositionTo);
        setChessPieceAtPosition(kingChessPieceFrom, kingPositionTo);
        setChessPieceAtPosition(null, rookPositionFrom);
        setChessPieceAtPosition(null, kingPositionFrom);
        nowPlayerColor = nowPlayerColor.opposite();
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayerColor);
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        System.out.println("-------------------------------------");

        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + "|\t");
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
        System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH");
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

    private boolean isKingUnderAttackByColor(Color attacker) {
        Position vulnerableKingPosition = findKingByColor(attacker.opposite());
        return isUnderAttackByColor(vulnerableKingPosition, attacker);
    }

    private Position findKingByColor(Color findingKingColor) {
        for (int i = 0; i < ChessBoard.SIZE; i++) {
            for (int j = 0; j < ChessBoard.SIZE; j++) {
                Position position = new Position(i, j);
                ChessPiece chessPiece = getChessPieceAtPosition(position);

                if (chessPiece == null) {
                    continue;
                }

                if (!chessPiece.getColor().equals(findingKingColor)) {
                    continue;
                }

                if (!(chessPiece instanceof King)) {
                    continue;
                }

                return position;
            }
        }

        throw new IllegalStateException("Критическая ошибка: на доске не может не быть короля");
    }

    private boolean isUnderAttackByColor(Position to, Color attacker) {
        for (int i = 0; i < ChessBoard.SIZE; i++) {
            for (int j = 0; j < ChessBoard.SIZE; j++) {
                Position from = new Position(i, j);
                ChessPiece attackerChessPiece = getChessPieceAtPosition(from);
                if (attackerChessPiece == null) {
                    continue;
                }

                if (!attackerChessPiece.getColor().equals(attacker)) {
                    continue;
                }

                try {
                    attackerChessPiece.validateMove(this, from, to);
                    return true;
                } catch (IllegalMoveException ignore) {
                    // Пропускаем исключение, мы просто пытаемся понять, можно ли так ходить.
                }
            }
        }

        return false;
    }
}
