package ru.vspochernin.model;

import ru.vspochernin.utils.MoveParser;

public record Move(Position from, Position to) {

    public static Move of(String moveStr) {
        return MoveParser.parseMove(moveStr);
    }
}
