package com.cleanarch.infra.exception;

import br.com.jpcchaves.core.exception.BadRequestException;
import br.com.jpcchaves.core.exception.InternalServerError;
import com.cleanarch.infra.exception.dto.ExceptionResponseDTO;
import jakarta.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(
      CustomExceptionHandler.class);

  @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      Object body,
      HttpHeaders headers,
      HttpStatusCode statusCode,
      WebRequest request
  ) {

    logger.error(ex.getMessage());
    logger.error(Arrays.toString(ex.getStackTrace()));

    ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();

    exceptionResponseDTO.setTimestamp(new Date());
    exceptionResponseDTO.setMessage(ex.getMessage());
    exceptionResponseDTO.setDetails(request.getDescription(false));

    return new ResponseEntity<>(exceptionResponseDTO,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({DataIntegrityViolationException.class,
      ConstraintViolationException.class, SQLException.class,})
  protected ResponseEntity<Object> handleExceptionDataIntegrity(Exception ex) {

    logger.error(ex.getCause().getLocalizedMessage());

    ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();

    StringBuilder errorMsg = new StringBuilder();

    if (ex instanceof DataIntegrityViolationException) {

      errorMsg.append(ex.getCause()
          .getMessage());

    } else if (ex instanceof ConstraintViolationException) {

      errorMsg.append(ex.getCause()
          .getMessage());

    } else if (ex instanceof SQLException) {

      errorMsg.append(ex.getCause()
          .getMessage());

    }

    exceptionResponseDTO.setTimestamp(new Date());
    exceptionResponseDTO.setMessage(errorMsg.toString());
    exceptionResponseDTO.setDetails(ex.getMessage());

    return new ResponseEntity<>(exceptionResponseDTO,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<ExceptionResponseDTO> handleAccessDeniedException(
      AccessDeniedException ex,
      WebRequest request
  ) {
    ExceptionResponseDTO exceptionResponse =
        new ExceptionResponseDTO(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ExceptionResponseDTO> handleBadRequestException(
      BadRequestException ex,
      WebRequest request
  ) {
    ExceptionResponseDTO exceptionResponse =
        new ExceptionResponseDTO(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InternalServerError.class)
  public ResponseEntity<ExceptionResponseDTO> handleInternalServerError(
      InternalServerError ex,
      WebRequest request
  ) {
    ExceptionResponseDTO exceptionResponse =
        new ExceptionResponseDTO(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request
  ) {
    ExceptionResponseDTO exceptionResponse =
        new ExceptionResponseDTO(
            new Date(),
            Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
