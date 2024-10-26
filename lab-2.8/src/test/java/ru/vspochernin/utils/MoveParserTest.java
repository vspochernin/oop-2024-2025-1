package ru.vspochernin.utils;

import org.junit.jupiter.api.Test;
import ru.vspochernin.model.Move;
import ru.vspochernin.model.Position;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pochernin-vla
 */
class MoveParserTest {

    @Test
    public void testCorrectParsingFromNumbers() {
        Move move = MoveParser.parseMove("1 4 3 4");

        assertEquals(move.from(), new Position(1, 4));
        assertEquals(move.to(), new Position(3, 4));
    }

    @Test
    public void testCorrectParsingFromNotation() {
        Move move = MoveParser.parseMove("e2 e4");

        assertEquals(move.from(), new Position(1, 4));
        assertEquals(move.to(), new Position(3, 4));
    }

    @Test
    public void testIncorrectParsing() {
        assertThrows(IllegalArgumentException.class, () -> MoveParser.parseMove("move 1 4 3 4"));
        assertThrows(IllegalArgumentException.class, () -> MoveParser.parseMove("1 e 4 r"));
        assertThrows(IllegalArgumentException.class, () -> MoveParser.parseMove("a2a8asduaj2o"));
        assertThrows(IllegalArgumentException.class, () -> MoveParser.parseMove("e2 e4 e5"));
        assertThrows(IllegalArgumentException.class, () -> MoveParser.parseMove("1 4 3 4 5"));
    }
}