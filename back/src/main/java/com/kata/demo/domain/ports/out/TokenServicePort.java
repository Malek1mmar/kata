package com.kata.demo.domain.ports.out;

public interface TokenServicePort {
  String generateToken(String email);
  String extractEmail(String token);
}