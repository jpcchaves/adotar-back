package com.cleanarch.infra.config.security;

import com.cleanarch.infra.exception.dto.ExceptionResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntrypointImpl implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(
      AuthenticationEntrypointImpl.class);

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException
  ) throws IOException, ServletException {

    ObjectMapper objectMapper = new ObjectMapper();

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    ExceptionResponseDTO exceptionResponseDTO = ExceptionResponseDTO
        .builder()
        .setMessage("Unauthorized!")
        .setDetails("You need to authenticate to access this resource!")
        .build();

    response
        .getWriter()
        .write(
            objectMapper
                .writer()
                .writeValueAsString(exceptionResponseDTO)
        );

    logger.error("Error in class: " + this.getClass().getSimpleName() + ". "
        + exceptionResponseDTO);
  }
}
