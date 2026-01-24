package com.kata.demo.application.service;

import com.kata.demo.domain.model.Product;
import com.kata.demo.domain.ports.in.ManageProductUseCase;
import com.kata.demo.domain.ports.out.ProductRepositoryPort;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService implements ManageProductUseCase {
  private final ProductRepositoryPort productRepository;

  public ProductService(ProductRepositoryPort productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable"));
  }

  @Override
  public Product createProduct(Product product) {
    checkAdmin();
    return productRepository.save(product);
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    checkAdmin();
    if (productRepository.findById(id).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return productRepository.save(product);
  }

  @Override
  public void deleteProduct(Long id) {
    checkAdmin();
    productRepository.deleteById(id);
  }

  private void checkAdmin() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !"admin@admin.com".equals(auth.getName())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Accès réservé à admin@admin.com");
    }
  }
}