package br.com.jpcchaves.core.domain.model;

public class UserPicture extends Picture {
  private User user;

  public UserPicture() {
  }

  public UserPicture(
      Long id,
      String name,
      long size,
      String type,
      String imgUrl,
      User user
  ) {
    super(id, name, size, type, imgUrl);
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
