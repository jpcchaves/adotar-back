package br.com.jpcchaves.core.exception;

import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;

public class InternalServerError extends BaseException {

  public InternalServerError(ExceptionDefinition exceptionDefinition) {
    super(exceptionDefinition);
  }
}
