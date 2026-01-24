package com.kata.demo.domain.ports.out;

public interface PasswordEncoderPort {
  String encode(String rawPassword);
  boolean matches(String rawPassword, String encodedPassword);
}