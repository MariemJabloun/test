package com.canalplus.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {


  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> notFoundHandler(NotFoundException ex) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

  @ExceptionHandler(NotNullException.class)
  public ResponseEntity<Object> notNullHandler(NotNullException ex) {

    return ResponseEntity
        .status(HttpStatus.NOT_ACCEPTABLE)
        .body(ex.getMessage());
  }

  @ExceptionHandler(ExistException.class)
  public ResponseEntity<Object> handleExistException(
      Exception ex, WebRequest request) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ex.getMessage());
  }

  @ExceptionHandler(NotAllowedException.class)
  public ResponseEntity<Object> handleActionNotAllowedException(
      Exception ex, WebRequest request) {

    return ResponseEntity
        .status(HttpStatus.NOT_ACCEPTABLE)
        .body(ex.getMessage());
  }

  @ExceptionHandler(InternalServerError.class)
  public ResponseEntity<Object> handleInternalServerException(
      Exception ex, WebRequest request) {

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ex.getMessage());
  }

}
