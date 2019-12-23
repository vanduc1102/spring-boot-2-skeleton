package com.wizeline.skeleton.constant;

public enum ResponseCode {
  SUCCESS("200-000", "Success."),
  INVALID_ARGUMENT("400-000", "Invalid arguments"),
  UNEXPECTED_ERROR("500-000", "Unexpected error."),
  REQUEST_TIMEOUT("408-001", "Request timeout."),
  SERVICE_UNAVAILABLE("503-004", "Service is unavailable."),
  NOT_FOUND_HANDLER("404", "Not found handler.");

  private final String code;
  private final String message;

  ResponseCode(String responseCode, String responseMessage) {
    this.code = responseCode;
    this.message = responseMessage;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
