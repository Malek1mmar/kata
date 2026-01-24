package com.kata.demo.domain.ports.out;

import com.kata.demo.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
  List<Product> findAll();
  Product save(Product product);
  Optional<Product> findById(Long id);
  void deleteById(Long id);
}