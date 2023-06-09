package com.jpcchaves.adotar.domain.Enum;

public enum HealthCondition {
    CHRONIC_DISEASE('C'),
    INJURED('I'),
    SICK('S'),
    PREGNANT('P'),
    HEALTHY('L');

    private final char healthCondition;

    HealthCondition(char healthCondition) {
        this.healthCondition = healthCondition;
    }

    public static HealthCondition valueOf(char healthCondition) {
        for (HealthCondition value : HealthCondition.values()) {
            if (healthCondition == value.getHealthCondition()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + healthCondition);
    }

    public char getHealthCondition() {
        return healthCondition;
    }
}
