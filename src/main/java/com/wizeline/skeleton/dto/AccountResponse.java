package com.wizeline.skeleton.dto;

import com.wizeline.skeleton.constant.ResponseCode;

public class AccountResponse extends GenericResponse<AccountDto> {

  public AccountResponse(ResponseCode responseCode, AccountDto data) {
    super(responseCode, data);
  }
}
