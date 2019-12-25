package com.wizeline.skeleton.service;

import com.wizeline.skeleton.dto.AccountDto;
import com.wizeline.skeleton.mapper.AccountMapper;
import com.wizeline.skeleton.persistence.entity.AccountEntity;
import com.wizeline.skeleton.persistence.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
  @Autowired private AccountRepository accountRepository;

  @Autowired private AccountMapper accountMapper;

  public AccountServiceImpl() {}

  @Override
  public AccountDto save(AccountDto account) {
    AccountEntity entity = accountRepository.save(accountMapper.toEntity(account));
    return accountMapper.toDto(entity);
  }

  public AccountDto getOne(Long accountId) {
    AccountDto account = new AccountDto();
    account.setId(accountId);
    return account;
  }

  @Override
  public List<AccountDto> findAll() {
    return accountMapper.fromEntityToDtoList(accountRepository.findAll());
  }
}
