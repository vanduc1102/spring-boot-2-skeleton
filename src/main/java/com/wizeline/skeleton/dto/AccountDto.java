package com.wizeline.skeleton.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
public class AccountDto {

  private Long id;
  private String status;

  @NotNull(message = "409|Username cannot be null")
  private String username;

  @NotNull(message = "410|First name cannot be null")
  private String firstname;

  @NotNull(message = "411|Last name cannot be null")
  private String lastname;

  @Email(message = "412|Email should be valid")
  private String email;
}
