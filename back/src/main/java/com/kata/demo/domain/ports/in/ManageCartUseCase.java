package com.kata.demo.domain.ports.in;

import com.kata.demo.domain.model.Product;
import java.util.List;

public interface ManageCartUseCase {
  List<Product> getCart();
  void addToCart(Long productId);
  void removeFromCart(Long productId);

  List<Product> getWishlist();
  void addToWishlist(Long productId);
  void removeFromWishlist(Long productId);
}
