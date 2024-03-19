package com.jpcchaves.adotar.domain.Enum;

public enum ExpirationTime {
  FIVE_MINUTES(5),
  TEN_MINUTES(10),
  FIFTEEN_MINUTES(15);

  private final int minutes;

  ExpirationTime(int minutes) {
    this.minutes = minutes;
  }

  public int getMinutes() {
    return minutes;
  }
}
