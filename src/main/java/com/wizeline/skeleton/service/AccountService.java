package com.wizeline.skeleton.service;

import com.wizeline.skeleton.dto.AccountDto;
import java.util.List;

public interface AccountService {

  AccountDto save(AccountDto account);

  AccountDto getOne(Long id);

  List<AccountDto> findAll();
}
