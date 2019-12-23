package com.wizeline.skeleton.exception;

import com.wizeline.skeleton.constant.Namespace;
import com.wizeline.skeleton.constant.ResponseCode;
import com.wizeline.skeleton.dto.ErrorResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException {

  private String namespace;
  private String responseCode;
  private String responseMessage;
  private HttpStatus httpStatus;

  public BusinessException(ResponseCode responseCode, HttpStatus httpStatus) {
    this.namespace = Namespace.SKELETON_CODE;
    this.responseCode = responseCode.getCode();
    this.responseMessage = responseCode.getMessage();
    this.httpStatus = httpStatus;
  }

  public BusinessException(
      String namespace, String responseCode, String responseMessage, HttpStatus httpStatus) {
    this.namespace = namespace;
    this.responseCode = responseCode;
    this.responseMessage = responseMessage;
    this.httpStatus = httpStatus;
  }

  public BusinessException(String namespace, ErrorResponse errorResponse, HttpStatus httpStatus) {
    this.namespace = namespace;
    this.responseCode = errorResponse.getCode();
    this.responseMessage = errorResponse.getMessage();
    this.httpStatus = httpStatus;
  }

  public BusinessException(String responseCode, String responseMessage, HttpStatus httpStatus) {
    this.namespace = Namespace.SKELETON_CODE;
    this.responseCode = responseCode;
    this.responseMessage = responseMessage;
    this.httpStatus = httpStatus;
  }
}
