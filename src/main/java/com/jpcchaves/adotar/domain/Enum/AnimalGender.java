package com.jpcchaves.adotar.domain.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AnimalGender {
    FEMALE('F'),
    MALE('M');

    private final char gender;

    AnimalGender(char gender) {
        this.gender = gender;
    }

    @JsonCreator
    public static AnimalGender fromValue(char gender) {
        for (AnimalGender sex : AnimalGender.values()) {
            if (sex.getGender() == gender) {
                return sex;
            }
        }
        throw new IllegalArgumentException("Invalid Sex value: " + gender);
    }

    @JsonValue
    public char getGender() {
        return gender;
    }
}
