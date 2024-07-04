package com.fakepi.ShoeStore.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Service
public class LoginRequest {
  @NotBlank(message = "Username required")
  private String username;
  @NotBlank(message = "Password required")
  private String password;
}
