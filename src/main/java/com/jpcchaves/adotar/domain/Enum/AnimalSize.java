package com.jpcchaves.adotar.domain.Enum;

public enum AnimalSize {
    TINY('T'),
    SMALL('S'),
    MEDIUM('M'),
    LARGE('L');

    private final char size;

    AnimalSize(char size) {
        this.size = size;
    }

    public static AnimalSize valueOf(char size) {
        for (AnimalSize value : AnimalSize.values()) {
            if (size == value.getSize()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid animal size value: " + size);
    }

    public char getSize() {
        return size;
    }
}
