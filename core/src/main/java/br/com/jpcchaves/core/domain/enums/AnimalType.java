package br.com.jpcchaves.core.domain.enums;

import java.util.Objects;

public enum AnimalType {
  DOG("D"),
  CAT("C"),
  BIRD("B");

  private final String animalType;

  AnimalType(String animalType) {
    this.animalType = animalType;
  }

  private static String fromValue(String animalType) {

    for (AnimalType type : values()) {

      if (Objects.equals(type.animalType, animalType)) {

        return animalType;
      }
    }

    throw new IllegalArgumentException("Invalid animal type!");
  }

  public String getAnimalType() {
    return animalType;
  }

  @Override
  public String toString() {
    return "AnimalType{" +
        "animalType='" + animalType + '\'' +
        '}';
  }
}
