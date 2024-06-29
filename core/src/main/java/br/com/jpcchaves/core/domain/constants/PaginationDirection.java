package br.com.jpcchaves.core.domain.constants;

public abstract class PaginationDirection {

  public static String ASCENDING = "ASC";
  public static String DESCENDING = "DESC";

  private PaginationDirection() {
  }

  public static String getDefault() {
    return PaginationDirection.ASCENDING;
  }
}
