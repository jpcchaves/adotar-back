package com.jpcchaves.adotar.domain.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AnimalSize {
  TINY('T'),
  SMALL('S'),
  MEDIUM('M'),
  LARGE('L');

  private final char size;

  AnimalSize(char size) {
    this.size = size;
  }

  @JsonCreator
  public static AnimalSize fromValue(char size) {
    for (AnimalSize value : AnimalSize.values()) {
      if (size == value.getSize()) {
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid animal size value: " + size);
  }

  @JsonValue
  public char getSize() {
    return size;
  }
}
