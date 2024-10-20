package ru.vspochernin.model;

public enum Color {

    WHITE("White"),
    BLACK("Black");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Color swapColor() {
        return this.equals(WHITE) ? BLACK : WHITE;
    }

    @Override
    public String toString() {
        return value;
    }
}
