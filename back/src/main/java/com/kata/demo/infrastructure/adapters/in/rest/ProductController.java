package com.kata.demo.infrastructure.adapters.in.rest;

import com.kata.demo.domain.model.Product;
import com.kata.demo.domain.ports.in.ManageProductUseCase;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ManageProductUseCase manageProductUseCase;

  public ProductController(ManageProductUseCase manageProductUseCase) {
    this.manageProductUseCase = manageProductUseCase;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    return ResponseEntity.ok(manageProductUseCase.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getOne(@PathVariable Long id) {
    return ResponseEntity.ok(manageProductUseCase.getProductById(id));
  }

  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(manageProductUseCase.createProduct(product));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
    return ResponseEntity.ok(manageProductUseCase.updateProduct(id, product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    manageProductUseCase.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}