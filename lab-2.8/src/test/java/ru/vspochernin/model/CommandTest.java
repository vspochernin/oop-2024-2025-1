package ru.vspochernin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    public void parseExitCommand() {
        String commandStr = "exit";
        Command command = Command.parse(commandStr);
        assertEquals(Command.EXIT, command);
    }

    @Test
    public void parseReplayCommand() {
        String commandStr = "replay";
        Command command = Command.parse(commandStr);
        assertEquals(Command.REPLAY, command);
    }

    @Test
    public void parseCastling0Command() {
        String commandStr = "castling0";
        Command command = Command.parse(commandStr);
        assertEquals(Command.CASTLING0, command);
    }

    @Test
    public void parseCastling7Command() {
        String commandStr = "castling7";
        Command command = Command.parse(commandStr);
        assertEquals(Command.CASTLING7, command);
    }

    @Test
    public void parseMoveCommand() {
        String commandStr = "move e2 e4";
        Command command = Command.parse(commandStr);
        assertEquals(Command.MOVE, command);
    }

    @Test
    public void parseUnknownCommands() {
        assertEquals(Command.UNKNOWN, Command.parse(""));
        assertEquals(Command.UNKNOWN, Command.parse("jump"));
        assertEquals(Command.UNKNOWN, Command.parse("move"));
    }
}