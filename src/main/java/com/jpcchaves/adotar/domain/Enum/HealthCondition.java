package com.jpcchaves.adotar.domain.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum HealthCondition {
    CHRONIC_DISEASE('C'),
    INJURED('I'),
    SICK('S'),
    PREGNANT('P'),
    HEALTHY('H');

    private final char healthCondition;

    HealthCondition(char healthCondition) {
        this.healthCondition = healthCondition;
    }

    @JsonCreator
    public static HealthCondition fromValue(char healthCondition) {
        for (HealthCondition value : HealthCondition.values()) {
            if (healthCondition == value.getHealthCondition()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid animal health condition value: " + healthCondition);
    }

    @JsonValue
    public char getHealthCondition() {
        return healthCondition;
    }
}
