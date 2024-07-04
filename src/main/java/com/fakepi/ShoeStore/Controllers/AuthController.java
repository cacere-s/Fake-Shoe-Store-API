package com.fakepi.ShoeStore.Controllers;

import com.fakepi.ShoeStore.Service.AuthService;
import com.fakepi.ShoeStore.Dtos.LoginRequest;
import com.fakepi.ShoeStore.Dtos.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  ResponseEntity login(@RequestBody @Valid LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  ResponseEntity register(@RequestBody @Valid RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }
}
