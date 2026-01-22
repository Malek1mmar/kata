package com.kata.demo.infrastructure.adapters.in.rest;

import com.kata.demo.domain.model.User;
import com.kata.demo.domain.ports.in.CreateAccountUseCase;
import com.kata.demo.infrastructure.adapters.in.rest.dto.AccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  private final CreateAccountUseCase createAccountUseCase;

  public AccountController(CreateAccountUseCase createAccountUseCase) {
    this.createAccountUseCase = createAccountUseCase;
  }

  @PostMapping
  public ResponseEntity<String> register(@RequestBody AccountRequest request) {
    User user = new User(
        null,
        request.username(),
        request.firstname(),
        request.email(),
        request.password()
    );

    createAccountUseCase.execute(user);

    return ResponseEntity.ok("Compte créé avec succès");
  }
}