package com.kata.demo.application.service;

import com.kata.demo.domain.model.Product;
import com.kata.demo.domain.model.User;
import com.kata.demo.domain.ports.in.ManageCartUseCase;
import com.kata.demo.domain.ports.out.ProductRepositoryPort;
import com.kata.demo.domain.ports.out.UserRepositoryPort;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CartService implements ManageCartUseCase {

  private final UserRepositoryPort userRepositoryPort;
  private final ProductRepositoryPort productRepositoryPort;

  public CartService(UserRepositoryPort userRepositoryPort, ProductRepositoryPort productRepositoryPort) {
    this.userRepositoryPort = userRepositoryPort;
    this.productRepositoryPort = productRepositoryPort;
  }


  @Override
  public List<Product> getCart() {
    return getCurrentUser().getCart();
  }

  @Override
  @Transactional
  public void addToCart(Long productId) {
    User user = getCurrentUser();
    Product product = getProductById(productId);

    if (user.getCart().stream().noneMatch(p -> p.getId().equals(productId))) {
      user.getCart().add(product);
      userRepositoryPort.save(user);
    }
  }

  @Override
  @Transactional
  public void removeFromCart(Long productId) {
    User user = getCurrentUser();
    boolean removed = user.getCart().removeIf(p -> p.getId().equals(productId));

    if (removed) {
      userRepositoryPort.save(user);
    }
  }


  @Override
  public List<Product> getWishlist() {
    return getCurrentUser().getWishlist();
  }

  @Override
  @Transactional
  public void addToWishlist(Long productId) {
    User user = getCurrentUser();
    Product product = getProductById(productId);

    if (user.getWishlist().stream().noneMatch(p -> p.getId().equals(productId))) {
      user.getWishlist().add(product);
      userRepositoryPort.save(user);
    }
  }

  @Override
  @Transactional
  public void removeFromWishlist(Long productId) {
    User user = getCurrentUser();
    boolean removed = user.getWishlist().removeIf(p -> p.getId().equals(productId));

    if (removed) {
      userRepositoryPort.save(user);
    }
  }

  private User getCurrentUser() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return userRepositoryPort.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
  }

  private Product getProductById(Long productId) {
    return productRepositoryPort.findById(productId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé"));
  }
}