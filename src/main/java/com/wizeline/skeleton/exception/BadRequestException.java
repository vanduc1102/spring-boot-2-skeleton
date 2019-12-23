package com.wizeline.skeleton.exception;

import com.wizeline.skeleton.constant.ResponseCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BusinessException {

  public BadRequestException(ResponseCode responseCode) {
    super(
        responseCode.getCode(), responseCode.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
