package com.wizeline.skeleton.dto;

import com.wizeline.skeleton.constant.Namespace;
import com.wizeline.skeleton.constant.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T> {

  private String namespace = Namespace.SKELETON_CODE;
  private String code;
  private String message;
  private T data;

  public GenericResponse() {
  }

  public GenericResponse(ResponseCode responseCode, T data) {
    this.code = responseCode.getCode();
    this.message = responseCode.getMessage();
    this.data = data;
  }

  public GenericResponse(String code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public GenericResponse(String namespace, String code, String message, T data) {
    this.namespace = namespace;
    this.code = code;
    this.message = message;
    this.data = data;
  }
}
