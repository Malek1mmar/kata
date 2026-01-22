package com.kata.demo.infrastructure.adapters.in.rest.dto;

public record AccountRequest(
    String username,
    String firstname,
    String email,
    String password
) {}
