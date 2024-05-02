package com.jpcchaves.adotar.infrastructure.presentation.error;

import com.jpcchaves.adotar.infrastructure.domain.exception.BadRequestException;
import com.jpcchaves.adotar.infrastructure.domain.exception.PasswordsMismatchException;
import com.jpcchaves.adotar.infrastructure.domain.exception.PdfNotAvailableException;
import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.exception.TokenExpiredException;
import com.jpcchaves.adotar.infrastructure.domain.exception.UnexpectedErrorException;
import com.jpcchaves.adotar.infrastructure.domain.exception.UserNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.model.ExceptionResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.SendFailedException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  static Logger logger = Logger.getLogger("Exception");

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(
      Exception ex, WebRequest request) {
    logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(
        exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ExceptionResponse>
  handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnexpectedErrorException.class)
  public final ResponseEntity<ExceptionResponse> handleUnexpectedErrorException(
      UnexpectedErrorException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(
        exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ExceptionResponse> handleBadRequestException(
      BadRequestException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(
      AccessDeniedException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<ExceptionResponse> handleIllegalArgumentException(
      IllegalArgumentException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TokenExpiredException.class)
  public final ResponseEntity<ExceptionResponse> handleTokenExpiredException(
      TokenExpiredException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(
      UserNotFoundException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(PasswordsMismatchException.class)
  public final ResponseEntity<ExceptionResponse>
  handlePasswordsMismatchException(
      PasswordsMismatchException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SendFailedException.class)
  public final ResponseEntity<ExceptionResponse> handleSendFailedException(
      SendFailedException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PdfNotAvailableException.class)
  public final ResponseEntity<ExceptionResponse> handlePdfNotAvailableException(
      PdfNotAvailableException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(
        exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MailSendException.class)
  public final ResponseEntity<ExceptionResponse> handleMailSendException(
      MailSendException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(
        exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MailAuthenticationException.class)
  public final ResponseEntity<ExceptionResponse>
  handleMailAuthenticationException(
      MailAuthenticationException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(
        exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MessagingException.class)
  public final ResponseEntity<ExceptionResponse> handleMessagingException(
      MessagingException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(
        exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      Object body,
      HttpHeaders headers,
      HttpStatusCode statusCode,
      WebRequest request) {
    logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
    return super.handleExceptionInternal(
        ex, body, headers, statusCode, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
