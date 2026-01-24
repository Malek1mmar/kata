package com.kata.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

public class User {
  private Long id;
  private String username;
  private String firstname;
  private String email;
  private String password;
  private List<Product> cart = new ArrayList<>();
  private List<Product> wishlist = new ArrayList<>();


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

  public List<Product> getCart() {
    return cart;
  }
  public List<Product> getWishlist() {
    return wishlist;
  }

  public User() {}

  public void setId(Long id) { this.id = id; }
  public void setPassword(String password) { this.password = password; }


}