package br.com.jpcchaves.core.domain.enums;

public enum Gender {
  FEMALE('F'),
  MALE('M');

  private final char gender;

  Gender(char gender) {
    this.gender = gender;
  }

  public static Gender fromValue(char gender) {
    for (Gender sex : Gender.values()) {
      if (sex.getGender() == gender) {
        return sex;
      }
    }
    throw new IllegalArgumentException("Invalid gender value: " + gender);
  }

  public char getGender() {
    return gender;
  }
}
