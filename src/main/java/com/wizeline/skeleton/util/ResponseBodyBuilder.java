package com.wizeline.skeleton.util;

import static com.wizeline.skeleton.constant.ResponseCode.NOT_FOUND_HANDLER;
import static com.wizeline.skeleton.constant.ResponseCode.UNEXPECTED_ERROR;

import com.wizeline.skeleton.dto.ErrorResponse;

public class ResponseBodyBuilder {

  private ResponseBodyBuilder() {}

  public static ErrorResponse unexpectedError() {
    return new ErrorResponse(UNEXPECTED_ERROR);
  }

  public static ErrorResponse notFoundHandler() {
    return new ErrorResponse(NOT_FOUND_HANDLER);
  }
}
