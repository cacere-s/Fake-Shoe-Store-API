package com.fakepi.ShoeStore.Service;

import com.fakepi.ShoeStore.Auth.AuthResponse;
import com.fakepi.ShoeStore.Dtos.LoginRequest;
import com.fakepi.ShoeStore.Dtos.RegisterRequest;
import com.fakepi.ShoeStore.Helpers.UserRole;
import com.fakepi.ShoeStore.Jwt.JwtService;
import com.fakepi.ShoeStore.Models.ShoeModel;
import com.fakepi.ShoeStore.Models.UserModel;
import com.fakepi.ShoeStore.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);

    return AuthResponse.builder()
        .data(user)
        .token(token)
        .build();


  }

  public AuthResponse register(RegisterRequest request) {
    UserModel user = UserModel.builder()
        .name(request.getName())
        .username(request.getUsername().toLowerCase())
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .rol(UserRole.USER)
        .shoe(null)
        .build();

    userRepository.save(user);

    return AuthResponse.builder()
        .data(user)
        .token(jwtService.getToken(user))
        .build();


  }
}
