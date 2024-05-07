package br.com.jpcchaves.core.domain;

public class Role {

  private Long id;
  private Role role;

  public Role(Role role) {
    this.role = role;
  }

  public Role(Long id, Role role) {
    this.id = id;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
