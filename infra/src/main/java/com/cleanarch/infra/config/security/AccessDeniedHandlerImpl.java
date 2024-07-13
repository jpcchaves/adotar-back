package com.cleanarch.infra.config.security;

import com.cleanarch.infra.exception.dto.ExceptionResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException
  ) throws IOException, ServletException {

    ObjectMapper objectMapper = new ObjectMapper();

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
        new Date(),
        "Access denied!",
        "You do not have access to this resource!"
    );

    response
        .getWriter()
        .write(
            objectMapper
                .writer()
                .writeValueAsString(exceptionResponseDTO)
        );
  }
}
