package com.wizeline.skeleton.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wizeline.skeleton.constant.ResponseCode;
import com.wizeline.skeleton.dto.ErrorResponse;
import com.wizeline.skeleton.dto.GenericResponse;
import com.wizeline.skeleton.exception.BusinessException;
import com.wizeline.skeleton.util.ResponseBodyBuilder;
import java.util.List;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(BusinessException.class)
  public <E extends BusinessException>
  ResponseEntity<GenericResponse> handleECatalogException(E ex) {
    val commonResponse =
        new ErrorResponse(ex.getNamespace(), ex.getResponseCode(), ex.getResponseMessage());
    return ResponseEntity.status(ex.getHttpStatus()).body(commonResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public GenericResponse handleArgumentNotValid(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    String errorMessage = null;
    String errorCode = null;
    for (FieldError fieldError : fieldErrors) {
      String fieldErrorMessage = fieldError.getDefaultMessage();
      if (StringUtils.isNotEmpty(fieldErrorMessage)) {
        String[] msgPart = fieldErrorMessage.split("\\|");
        if (msgPart.length == 2) {
          errorMessage = msgPart[1];
          errorCode = msgPart[0];
          break;
        }
      }
    }
    return new ErrorResponse(errorCode, errorMessage);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public GenericResponse handleArgumentNotValid(HttpMessageNotReadableException ex) {
    if (ex.getCause() instanceof InvalidFormatException) {
      return new ErrorResponse(
          ResponseCode.INVALID_ARGUMENT.getCode(), ex.getMessage());
    } else {
      LOGGER.error(ex.getMessage(), ex);
      throw ex;
    }
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected GenericResponse handleUnexpectedError(Exception ex) {
    LOGGER.error("handleUnexpectedError: " + ex.getMessage(), ex);
    return ResponseBodyBuilder.unexpectedError();
  }
}
