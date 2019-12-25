package com.wizeline.skeleton.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity extends BaseEntity {

  private String status;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
}
