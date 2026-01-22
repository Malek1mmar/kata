package com.kata.demo.infrastructure.adapters.out.security;

import com.kata.demo.domain.ports.out.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

  private final BCryptPasswordEncoder encoder;

  public BCryptPasswordEncoderAdapter() {
    this.encoder = new BCryptPasswordEncoder();
  }

  @Override
  public String encode(String rawPassword) {
    return encoder.encode(rawPassword);
  }
}