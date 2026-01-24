package com.kata.demo.domain.ports.in;

public interface LoginUseCase {
  String login(String email, String password);
}