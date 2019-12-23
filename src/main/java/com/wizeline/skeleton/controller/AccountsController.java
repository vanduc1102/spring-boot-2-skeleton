package com.wizeline.skeleton.controller;

import com.wizeline.skeleton.constant.ResponseCode;
import com.wizeline.skeleton.dto.AccountDto;
import com.wizeline.skeleton.dto.AccountResponse;
import com.wizeline.skeleton.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/skeleton/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

  @Autowired
  @Qualifier(value = "githubRestTemplate")
  private RestTemplate githubRestTemplate;

  @ResponseBody
  @GetMapping(value = "/")
  public AccountResponse getAccount(@PathVariable("id") String accountId) {
    return new AccountResponse(ResponseCode.SUCCESS, new AccountDto());
  }

  @ResponseBody
  @GetMapping(value = "/github/")
  public GenericResponse<String> getGithubAccount(@PathVariable("username") String username) {
    return new GenericResponse<>(ResponseCode.SUCCESS,"");
  }
}
