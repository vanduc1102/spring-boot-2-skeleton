package com.wizeline.skeleton.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

  private String status;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
}
