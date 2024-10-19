package ru.vspochernin.model;

import java.util.Objects;

public record Position(int line, int column) {

    public boolean isOutOfBounds() {
        return line < 0 || line > 7 || column < 0 || column > 7;
    }

    public Position relative(int lineDiff, int columnDiff) {
        return new Position(line + lineDiff, column + columnDiff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;
        return line == position.line && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }
}
