package ru.vspochernin.utils;

import org.junit.jupiter.api.Test;
import ru.vspochernin.model.Move;
import ru.vspochernin.model.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveParserTest {

    @Test
    public void testCorrectParsingFromNumbers() {
        Move move = Move.of("1 4 3 4");

        assertEquals(move.from(), new Position(1, 4));
        assertEquals(move.to(), new Position(3, 4));
    }

    @Test
    public void testCorrectParsingFromNotation() {
        Move move = Move.of("e2 e4");

        assertEquals(move.from(), new Position(1, 4));
        assertEquals(move.to(), new Position(3, 4));
    }

    @Test
    public void testIncorrectParsing() {
        assertThrows(IllegalArgumentException.class, () -> Move.of("move 1 4 3 4"));
        assertThrows(IllegalArgumentException.class, () -> Move.of("1 e 4 r"));
        assertThrows(IllegalArgumentException.class, () -> Move.of("a2a8asduaj2o"));
        assertThrows(IllegalArgumentException.class, () -> Move.of("e2 e4 e5"));
        assertThrows(IllegalArgumentException.class, () -> Move.of("1 4 3 4 5"));
    }
}