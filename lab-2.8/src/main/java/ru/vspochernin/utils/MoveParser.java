package ru.vspochernin.utils;

import ru.vspochernin.model.Move;
import ru.vspochernin.model.Position;

public class MoveParser {

    public static Move parseMove(String moveStr) {
        try {
            String[] moveParts = moveStr.split(" ");
            Position from;
            Position to;

            // Пытаемся обработать строку вида "1 4 3 4".
            if (moveParts.length == 4) {
                int lineFrom = Integer.parseInt(moveParts[0]);
                int columnFrom = Integer.parseInt(moveParts[1]);
                int lineTo = Integer.parseInt(moveParts[2]);
                int columnTo = Integer.parseInt(moveParts[3]);

                from = new Position(lineFrom, columnFrom);
                to = new Position(lineTo, columnTo);

                return new Move(from, to);
            }

            // Пытаемся обработать строку вида "e2 e4".
            if (moveParts.length == 2) {
                String fromInNotation = moveParts[0];
                String toInNotation = moveParts[1];

                if (fromInNotation.length() != 2 || toInNotation.length() != 2) {
                    throw new IllegalArgumentException();
                }

                from = new Position(parseLine(fromInNotation.charAt(1)), parseColumn(fromInNotation.charAt(0)));
                to = new Position(parseLine(toInNotation.charAt(1)), parseColumn(toInNotation.charAt(0)));

                return new Move(from, to);
            }
        } catch (Exception e) {
            // Пропускаем, выкинем дальше.
        }

        throw new IllegalArgumentException("Некорректно задан ход");
    }

    private static int parseLine(char lineInNotation) {
        return Integer.parseInt(String.valueOf(lineInNotation)) - 1;
    }

    private static int parseColumn(char columnInNotation) {
        return switch (columnInNotation) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> throw new IllegalStateException();
        };
    }
}
