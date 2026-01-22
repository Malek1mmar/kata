package com.kata.demo.service;

import com.kata.demo.domain.model.User;
import com.kata.demo.domain.ports.in.CreateAccountUseCase;
import com.kata.demo.domain.ports.out.PasswordEncoderPort;
import com.kata.demo.domain.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CreateAccountUseCase {

  private final UserRepositoryPort userRepositoryPort;
  private final PasswordEncoderPort passwordEncoderPort;

  public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
    this.userRepositoryPort = userRepositoryPort;
    this.passwordEncoderPort = passwordEncoderPort;
  }

  @Override
  public User execute(User user) {
    String encodedPassword = passwordEncoderPort.encode(user.getPassword());
    user.setPassword(encodedPassword);

    return userRepositoryPort.save(user);
  }
}
