package br.com.jpcchaves.core.exception;

import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;

public class BadRequestException extends BaseException {

  public BadRequestException(
      String message,
      String code,
      int httpStatus
  ) {
    super(message, code, httpStatus);
  }

  public BadRequestException(ExceptionDefinition exceptionDefinition) {
    super(exceptionDefinition);
  }
}
