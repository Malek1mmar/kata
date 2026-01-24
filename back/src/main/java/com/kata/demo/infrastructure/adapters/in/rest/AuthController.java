package com.kata.demo.infrastructure.adapters.in.rest;

import com.kata.demo.domain.ports.in.LoginUseCase;
import com.kata.demo.infrastructure.adapters.in.rest.dto.LoginRequest;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final LoginUseCase loginUseCase;

  public AuthController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  @PostMapping("/token")
  public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
    String token = loginUseCase.login(request.email(), request.password());
    return ResponseEntity.ok(Map.of("token", token));
  }
}