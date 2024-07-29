package com.cleanarch.infra.config.security;

import com.cleanarch.infra.exception.dto.ExceptionResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  private static final Logger logger =
      LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {

    ObjectMapper objectMapper = new ObjectMapper();

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    ExceptionResponseDTO exceptionResponseDTO =
        new ExceptionResponseDTO(
            new Date(),
            "Access denied!",
            "You do not have access to this resource!");

    response
        .getWriter()
        .write(objectMapper.writer().writeValueAsString(exceptionResponseDTO));

    logger.error(
        "Error in class: "
            + this.getClass().getSimpleName()
            + ". "
            + exceptionResponseDTO);
  }
}
