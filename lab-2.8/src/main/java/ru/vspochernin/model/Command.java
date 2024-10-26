package ru.vspochernin.model;

import java.util.regex.Pattern;

public enum Command {

    UNKNOWN("^$"),
    EXIT("^exit$"),
    REPLAY("^replay$"),
    CASTLING0("^castling0$"),
    CASTLING7("^castling7$"),
    MOVE("^move .*$"),
    ;

    private final String regex;

    Command(String regex) {
        this.regex = regex;
    }

    public static Command parse(String commandStr) {
        for (Command command : values()) {
            if (Pattern.compile(command.regex).matcher(commandStr).find()) {
                return command;
            }
        }
        return UNKNOWN;
    }
}
