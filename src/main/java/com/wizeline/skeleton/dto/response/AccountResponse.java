package com.wizeline.skeleton.dto.response;

import com.wizeline.skeleton.constant.ResponseCode;
import com.wizeline.skeleton.dto.AccountDto;

public class AccountResponse extends GenericResponse<AccountDto> {

  public AccountResponse(ResponseCode responseCode, AccountDto data) {
    super(responseCode, data);
  }
}
