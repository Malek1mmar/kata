package com.kata.demo.domain.model;

import lombok.Getter;

public class User {
  private Long id;
  private String username;
  private String firstname;
  private String email;
  private String password;

  public User(Long id, String username, String firstname, String email, String password) {
    this.id = id;
    this.username = username;
    this.firstname = firstname;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public User() {}

  public void setId(Long id) { this.id = id; }
  public void setPassword(String password) { this.password = password; }


}