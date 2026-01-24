package com.kata.demo.service;

import com.kata.demo.domain.model.User;
import com.kata.demo.domain.ports.in.CreateAccountUseCase;
import com.kata.demo.domain.ports.in.LoginUseCase;
import com.kata.demo.domain.ports.out.PasswordEncoderPort;
import com.kata.demo.domain.ports.out.TokenServicePort;
import com.kata.demo.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CreateAccountUseCase, LoginUseCase {

  private final UserRepositoryPort userRepositoryPort;
  private final PasswordEncoderPort passwordEncoderPort;
  private final TokenServicePort tokenServicePort;

  public UserService(UserRepositoryPort userRepositoryPort,
      PasswordEncoderPort passwordEncoderPort,
      TokenServicePort tokenServicePort) {
    this.userRepositoryPort = userRepositoryPort;
    this.passwordEncoderPort = passwordEncoderPort;
    this.tokenServicePort = tokenServicePort;
  }

  @Override
  public User execute(User user) {
    String encodedPassword = passwordEncoderPort.encode(user.getPassword());
    user.setPassword(encodedPassword);

    return userRepositoryPort.save(user);
  }

  @Override
  public String login(String email, String password) {
    User user = userRepositoryPort.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    if (!passwordEncoderPort.matches(password, user.getPassword())) {
      throw new RuntimeException("Mot de passe incorrect");
    }

    return tokenServicePort.generateToken(user.getEmail());
  }
}
