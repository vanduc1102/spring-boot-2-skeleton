package com.wizeline.skeleton.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
  private Long id;
  private String status;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
}
