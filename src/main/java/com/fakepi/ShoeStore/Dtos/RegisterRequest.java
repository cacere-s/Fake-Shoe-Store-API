package com.fakepi.ShoeStore.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @Size(min = 5, message = "Invalid name")
  private String name;
  @Pattern(regexp = "^[\\w](?!.*?\\.{2})[\\w.]{1,28}[\\w]$", message = "Invalid username")
  private String username;
  @Email(message = "Invalid email", regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")
  private String email;
  @Size(min = 8, message = "Invalid password")
  private String password;
}
