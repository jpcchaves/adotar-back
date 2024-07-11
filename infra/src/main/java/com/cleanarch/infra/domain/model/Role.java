package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;
import org.springframework.security.core.*;

import java.io.*;
import java.util.*;

@Entity
@Table(name = "roles")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1)
public class Role implements GrantedAuthority, Serializable {

  @Serial
  private static final long serialVersionUID = -4099401606873221148L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
  private Long id;

  @Column(nullable = false, length = 50)
  private String name;

  public Role() {
  }

  public Role(
      Long id,
      String name
  ) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getAuthority() {
    return this.name;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return Objects.equals(id, role.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
