package com.kata.demo.infrastructure.adapters.out.persistence;

import com.kata.demo.domain.model.InventoryStatus;
import com.kata.demo.domain.model.Product;
import com.kata.demo.domain.ports.out.ProductRepositoryPort;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class JpaProductAdapter implements ProductRepositoryPort {

  private final JpaProductRepository repository;

  public JpaProductAdapter(JpaProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Product> findAll() {
    return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
  }

  @Override
  public Optional<Product> findById(Long id) {
    return repository.findById(id).map(this::toDomain);
  }

  @Override
  public Product save(Product product) {
    ProductEntity entity = toEntity(product);
    ProductEntity saved = repository.save(entity);
    return toDomain(saved);
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }


  private Product toDomain(ProductEntity e) {
    return new Product();
  }

  private ProductEntity toEntity(Product p) {
    ProductEntity e = new ProductEntity();
    e.setId(p.getId());
    e.setCode(p.getCode());
    e.setName(p.getName());
    return e;
  }
}