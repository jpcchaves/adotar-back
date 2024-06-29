package br.com.jpcchaves.core.exception;

import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;

public class PetException extends BaseException {
  
  public PetException(
      String message,
      String code,
      int httpStatus
  ) {
    super(message, code, httpStatus);
  }

  public PetException(ExceptionDefinition exceptionDefinition) {
    super(exceptionDefinition);
  }
}
