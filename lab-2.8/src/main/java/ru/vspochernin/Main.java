package ru.vspochernin;

import java.util.Scanner;

import ru.vspochernin.board.ChessBoard;
import ru.vspochernin.model.Move;
import ru.vspochernin.utils.MoveParser;

public class Main {

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Чтобы проверить игру надо вводить команды:
                'exit' - для выхода
                'replay' - для перезапуска игры
                'castling0' или 'castling7' - для рокировки по соответствующей линии
                'move 1 4 3 4' или 'move e2 e4' - для передвижения фигуры с позиции 1 4 на 3 4 (поле это двумерный массив от 0 до 7).
                Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();

        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) {
                break;
            } else if (s.equals("replay")) {
                System.out.println("Заново");
                board = new ChessBoard();
                board.printBoard();
            } else {
                try {
                    if (s.equals("castling0")) {
                        if (board.castling0()) {
                            System.out.println("Рокировка удалась");
                            board.printBoard();
                        } else {
                            System.out.println("Рокировка не удалась");
                        }
                    } else if (s.equals("castling7")) {
                        if (board.castling7()) {
                            System.out.println("Рокировка удалась");
                            board.printBoard();
                        } else {
                            System.out.println("Рокировка не удалась");
                        }
                    } else if (s.startsWith("move ")) {
                        Move move = MoveParser.parseMove(s.substring(5));
                        if (board.moveToPosition(move)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else {
                            System.out.println("Передвижение не удалось");
                        }
                    } else {
                        System.out.println("Некорректная команды");
                    }
                } catch (Exception e) {
                    System.out.printf("Возникла следующая проблема: %s%n", e.getMessage());
                }
            }
        }
    }
}