package com.wizeline.skeleton.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private Long id;
  private String status;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
}
