package com.jpcchaves.adotar.domain.Enum;

public enum AnimalGender {
    FEMALE('F'),
    MALE('M');

    private final char gender;

    AnimalGender(char gender) {
        this.gender = gender;
    }

    public static AnimalGender valueOf(char gender) {
        for (AnimalGender value : AnimalGender.values()) {
            if (gender == value.getGender()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + gender);
    }

    public char getGender() {
        return gender;
    }
}
