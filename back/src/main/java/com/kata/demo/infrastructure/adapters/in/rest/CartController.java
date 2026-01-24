package com.kata.demo.infrastructure.adapters.in.rest;

import com.kata.demo.domain.model.Product;
import com.kata.demo.domain.ports.in.ManageCartUseCase;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CartController {

  private final ManageCartUseCase cartUseCase;

  public CartController(ManageCartUseCase cartUseCase) {
    this.cartUseCase = cartUseCase;
  }


  @GetMapping("/cart")
  public ResponseEntity<List<Product>> getCart() {
    return ResponseEntity.ok(cartUseCase.getCart());
  }

  @PostMapping("/cart/{productId}")
  public ResponseEntity<List<Product>> addToCart(@PathVariable Long productId) {
    cartUseCase.addToCart(productId);
    return ResponseEntity.ok(cartUseCase.getCart());
  }

  @DeleteMapping("/cart/{productId}")
  public ResponseEntity<Void> removeFromCart(@PathVariable Long productId) {
    cartUseCase.removeFromCart(productId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/wishlist")
  public ResponseEntity<List<Product>> getWishlist() {
    return ResponseEntity.ok(cartUseCase.getWishlist());
  }

  @PostMapping("/wishlist/{productId}")
  public ResponseEntity<Void> addToWishlist(@PathVariable Long productId) {
    cartUseCase.addToWishlist(productId);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/wishlist/{productId}")
  public ResponseEntity<Void> removeFromWishlist(@PathVariable Long productId) {
    cartUseCase.removeFromWishlist(productId);
    return ResponseEntity.ok().build();
  }
}