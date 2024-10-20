package ru.vspochernin.utils;

import ru.vspochernin.model.Move;
import ru.vspochernin.model.Position;

/**
 * @author pochernin-vla
 */
public class MoveParser {

    public static Move parseMove(String moveStr) {
        String[] a = moveStr.split(" ");
        try {
            int lineFrom = Integer.parseInt(a[1]);
            int columnFrom = Integer.parseInt(a[2]);
            int lineTo = Integer.parseInt(a[3]);
            int columnTo = Integer.parseInt(a[4]);

            Position from = new Position(lineFrom, columnFrom);
            Position to = new Position(lineTo, columnTo);

            return new Move(from, to);
        } catch (Exception ignored) {
        }

        String fromStr = a[1];
        String toStr = a[2];

        Position from = new Position(parseLine(fromStr.charAt(1)), parseColumn(fromStr.charAt(0)));
        Position to = new Position(parseLine(toStr.charAt(1)), parseColumn(toStr.charAt(0)));

        return new Move(from, to);
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
            default -> throw new IllegalStateException("Unexpected value: " + columnInNotation);
        };
    }
}
