package com.wizeline.skeleton.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

  private Long id;
  private String status;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
}
