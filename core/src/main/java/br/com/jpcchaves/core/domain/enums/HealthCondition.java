package br.com.jpcchaves.core.domain.enums;

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

  public static HealthCondition fromValue(char healthCondition) {
    for (HealthCondition value : HealthCondition.values()) {
      if (healthCondition == value.getHealthCondition()) {
        return value;
      }
    }
    throw new IllegalArgumentException(
        "Invalid animal health condition value: " + healthCondition);
  }

  public char getHealthCondition() {
    return healthCondition;
  }
}
