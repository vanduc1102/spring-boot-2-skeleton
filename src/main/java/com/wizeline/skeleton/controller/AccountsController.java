package com.wizeline.skeleton.controller;

import com.wizeline.skeleton.constant.ResponseCode;
import com.wizeline.skeleton.dto.AccountDto;
import com.wizeline.skeleton.dto.AccountResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/skeleton/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

  @ResponseBody
  @GetMapping(value = "/")
  public AccountResponse getAccount(@PathVariable("id") String accountId) {
    return new AccountResponse(ResponseCode.SUCCESS, new AccountDto());
  }
}
