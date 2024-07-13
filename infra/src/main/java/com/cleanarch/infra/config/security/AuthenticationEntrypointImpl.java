package com.cleanarch.infra.config.security;

import com.cleanarch.infra.exception.dto.ExceptionResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntrypointImpl implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException
  ) throws IOException, ServletException {

    ObjectMapper objectMapper = new ObjectMapper();

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
        new Date(),
        "Unauthorized!",
        "You need to authenticate to access this resource!"
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
