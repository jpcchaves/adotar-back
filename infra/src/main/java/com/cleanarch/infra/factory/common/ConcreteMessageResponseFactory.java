package com.cleanarch.infra.factory.common;

import com.cleanarch.usecase.common.dto.MessageResponseDTO;

public class ConcreteMessageResponseFactory {

  public static MessageResponseDTO buildMessage(String message) {
    return new MessageResponseDTO(message);
  }
}
