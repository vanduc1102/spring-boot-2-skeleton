package com.wizeline.skeleton.controller;

import com.wizeline.skeleton.constant.ResponseCode;
import com.wizeline.skeleton.dto.AccountDto;
import com.wizeline.skeleton.dto.response.AccountResponse;
import com.wizeline.skeleton.dto.response.GenericResponse;
import com.wizeline.skeleton.service.AccountService;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

  @Autowired
  @Qualifier(value = "githubRestTemplate")
  private RestTemplate githubRestTemplate;

  @Autowired private AccountService accountService;

  @ResponseBody
  @PostMapping(value = "/")
  public GenericResponse<AccountDto> save(@Valid @RequestBody AccountDto account) {
    AccountDto accountDto = accountService.save(account);
    return new GenericResponse<>(ResponseCode.SUCCESS, accountDto);
  }

  @ResponseBody
  @GetMapping(value = "/")
  public GenericResponse<Collection<AccountDto>> findAll() {
    List<AccountDto> accountDtoList = accountService.findAll();
    return new GenericResponse<>(ResponseCode.SUCCESS, accountDtoList);
  }

  @ResponseBody
  @GetMapping(value = "/{id}")
  public AccountResponse getAccount(@PathVariable("id") Long id) {
    AccountDto accountDto = accountService.getOne(id);
    return new AccountResponse(ResponseCode.SUCCESS, accountDto);
  }

  @ResponseBody
  @GetMapping(value = "/github/{username}")
  public GenericResponse<String> getGithubAccount(@PathVariable("username") String username) {
    return new GenericResponse<>(
        ResponseCode.SUCCESS,
        githubRestTemplate.getForObject("/users/".concat(username), String.class));
  }
}
