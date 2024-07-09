package com.cleanarch.usecase.role.dto;

public class CreateRoleRequestDTO {
  private String name;

  public CreateRoleRequestDTO() {}

  public CreateRoleRequestDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
