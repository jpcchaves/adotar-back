package com.cleanarch.usecase.role.dto;

public class BaseRoleRequestDTO {
  private String name;

  public BaseRoleRequestDTO() {
  }

  public BaseRoleRequestDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
