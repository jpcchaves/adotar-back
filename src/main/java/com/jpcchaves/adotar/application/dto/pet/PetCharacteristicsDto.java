package com.jpcchaves.adotar.application.dto.pet;

public class PetCharacteristicsDto {
  private Long id;
  private String characteristic;

  public PetCharacteristicsDto() {}

  public PetCharacteristicsDto(Long id, String characteristic) {
    this.id = id;
    this.characteristic = characteristic;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCharacteristic() {
    return characteristic;
  }

  public void setCharacteristic(String characteristic) {
    this.characteristic = characteristic;
  }
}
