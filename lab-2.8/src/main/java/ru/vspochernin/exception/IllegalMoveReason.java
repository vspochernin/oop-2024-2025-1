package ru.vspochernin.exception;

public enum IllegalMoveReason {

    OUT_OF_BOUNDS("Выход за границы доски"),
    FROM_EQUALS_TO("Начало и конец хода совпадают"),
    CHESS_PIECE_FROM_IS_NULL("Фигура для хода отсутствует"),
    EAT_YOUR_CHESS_PIECE("Попытка съесть свою фигуру"),
    NOT_YOUR_TURN("Сейчас не ваш ход"),
    BISHOP_ILLEGAL_MOVE("Слон так не ходит"),
    CHESS_PIECES_BETWEEN("На пути хода есть фигуры"),
    ROOK_ILLEGAL_MOVE("Ладья так не ходит"),
    CASTLING_NO_ROOK_OR_KING("Некорректное расположение ладьи и короля для рокировки"),
    CASTLING_TOUCHED("Ладья или Король для рокировки уже совершали ход"),
    PAWN_ILLEGAL_MOVE("Пешка так не ходит"),
    HORSE_ILLEGAL_MOVE("Конь так не ходит"),
    QUEEN_ILLEGAL_MOVE("Ферзь так не ходит"),
    KING_ILLEGAL_MOVE("Король так не ходит"),
    KING_UNDER_ATTACK("Король под ударом"),
    ;

    private final String description;

    IllegalMoveReason(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
