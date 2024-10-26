package ru.vspochernin;

import java.util.Scanner;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.model.Command;
import ru.vspochernin.model.Move;

public class Main {

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'replay' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move 1 4 3 4' или 'move e2 e4' - для передвижения фигуры с позиции 1 4 на 3 4, поле: [0..7][0..7]""");
        System.out.println();
        board.printBoard();

        Command command = Command.UNKNOWN;
        while (command != Command.EXIT) {
            String commandStr = scanner.nextLine();
            command = Command.parse(commandStr);

            try {
                switch (command) {
                    case UNKNOWN -> System.out.println("Некорректная команда, введите команду заново");
                    case EXIT -> System.out.println("Программа завершает свою работу");
                    case REPLAY -> {
                        System.out.println("Заново");
                        board = new ChessBoard();
                        board.printBoard();
                    }
                    case MOVE -> {
                        Move move = Move.of(commandStr.substring(5));
                        board.moveToPosition(move);
                        System.out.println("Ход удался");
                        board.printBoard();
                    }
                    case CASTLING0 -> {
                        board.castling0();
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    }
                    case CASTLING7 -> {
                        board.castling7();
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    }
                }
            } catch (Exception e) {
                System.out.printf("Возникла следующая проблема: %s, повторите ход:\n", e.getMessage());
            }
        }
    }
}