package br.com.jpcchaves.core.domain.model;

import br.com.jpcchaves.core.domain.constants.PaginationDirection;

public class PaginationRequest {

  private int page = 0;
  private int size = 10;
  private String sort = "";
  private String direction = PaginationDirection.getDefault();

  public PaginationRequest() {}

  public PaginationRequest(int page, int size, String sort, String direction) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.direction = direction;
  }

  public PaginationRequest(Builder builder) {
    this.page = builder.page;
    this.size = builder.size;
    this.sort = builder.sort;
    this.direction = builder.direction;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public Builder builder() {
    return new Builder();
  }

  public static class Builder {

    public int page = 0;
    public int size = 10;
    public String sort = "";
    public String direction = PaginationDirection.getDefault();

    public Builder() {}

    public Builder setPage(int page) {
      this.page = page;
      return this;
    }

    public Builder setSize(int size) {
      this.size = size;
      return this;
    }

    public Builder setSort(String sort) {
      this.sort = sort;
      return this;
    }

    public Builder setDirection(String direction) {
      this.direction = direction;
      return this;
    }

    public PaginationRequest build() {
      return new PaginationRequest(this);
    }
  }
}
