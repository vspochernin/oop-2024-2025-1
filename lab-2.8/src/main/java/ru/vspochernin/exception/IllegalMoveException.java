package ru.vspochernin.exception;

public class IllegalMoveException extends RuntimeException {

    private final IllegalMoveReason reason;

    public IllegalMoveException(IllegalMoveReason reason) {
        this.reason = reason;
    }

    public IllegalMoveReason getReason() {
        return reason;
    }

    @Override
    public String getMessage() {
        return reason.toString();
    }
}
