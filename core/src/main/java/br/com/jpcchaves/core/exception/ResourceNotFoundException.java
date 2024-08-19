package br.com.jpcchaves.core.exception;

import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;

public class ResourceNotFoundException extends BaseException {

  public ResourceNotFoundException(ExceptionDefinition exceptionDefinition) {
    super(exceptionDefinition);
  }

  public ResourceNotFoundException(
      String message, String code, int httpStatus) {
    super(message, code, httpStatus);
  }
}
