package br.com.jpcchaves.core.exception;

import br.com.jpcchaves.core.exception.enums.ExceptionDefinition;

public class AuthException extends BaseException {

  public AuthException(ExceptionDefinition exceptionDefinition) {
    super(exceptionDefinition);
  }
}
