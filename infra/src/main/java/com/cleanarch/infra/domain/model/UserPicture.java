package com.cleanarch.infra.domain.model;

import jakarta.persistence.*;

import java.io.*;

@Entity
@Table(name = "user_picture")
@PrimaryKeyJoinColumn(name = "id")
public class UserPicture extends Picture implements Serializable {

  @Serial
  private static final long serialVersionUID = 277342111142837805L;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL
  )
  @JoinColumn(
      name = "user_id",
      foreignKey = @ForeignKey(
          name = "user_fk",
          value = ConstraintMode.CONSTRAINT
      )
  )
  private User user;

  public UserPicture() {
  }

  public UserPicture(
      Long id,
      String fileName,
      long size,
      String type,
      String imgUrl,
      User user
  ) {
    super(id, fileName, size, type, imgUrl);
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserPicture{" +
        "user=" + user +
        '}';
  }
}
