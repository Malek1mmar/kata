package com.kata.demo.domain.ports.in;

import com.kata.demo.domain.model.User;

public interface CreateAccountUseCase {
  User execute(User user);
}