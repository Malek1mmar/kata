package com.kata.demo.domain.ports.in;

import com.kata.demo.domain.model.Product;
import java.util.List;

public interface ManageProductUseCase {
  List<Product> getAllProducts();
  Product createProduct(Product product);
  Product updateProduct(Long id, Product product);
  void deleteProduct(Long id);
  Product getProductById(Long id);
}