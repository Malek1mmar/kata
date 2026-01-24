package com.kata.demo.infrastructure.adapters.out.persistence;

import com.kata.demo.domain.model.User;
import com.kata.demo.domain.ports.out.UserRepositoryPort;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
  private final SpringDataUserRepository repository;
  private final JpaProductAdapter productAdapter;

  public JpaUserRepositoryAdapter(SpringDataUserRepository repository, JpaProductAdapter productAdapter) {
    this.repository = repository;
    this.productAdapter = productAdapter;
  }

  @Override
  public User save(User domainUser) {
    UserEntity entity = new UserEntity(
        domainUser.getId(),
        domainUser.getUsername(),
        domainUser.getFirstname(),
        domainUser.getEmail(),
        domainUser.getPassword()
    );
    if (domainUser.getCart() != null) {
      entity.setCart(domainUser.getCart().stream()
          .map(productAdapter::toEntity)
          .toList());
    }

    if (domainUser.getWishlist() != null) {
      entity.setWishlist(domainUser.getWishlist().stream()
          .map(productAdapter::toEntity)
          .toList());
    }
    UserEntity savedEntity = repository.save(entity);

    return new User(
        savedEntity.getId(),
        savedEntity.getUsername(),
        savedEntity.getFirstname(),
        savedEntity.getEmail(),
        savedEntity.getPassword()
    );
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email)
        .map(this::toDomain);
  }

  private User toDomain(UserEntity entity) {
    User user = new User(
        entity.getId(),
        entity.getUsername(),
        entity.getFirstname(),
        entity.getEmail(),
        entity.getPassword()
    );

    if (entity.getCart() != null) {
      user.setCart(entity.getCart().stream()
          .map(productAdapter::toDomain)
          .collect(Collectors.toCollection(ArrayList::new)));
    }

    if (entity.getWishlist() != null) {
      user.setWishlist(entity.getWishlist().stream()
          .map(productAdapter::toDomain)
          .collect(Collectors.toCollection(ArrayList::new)));
    }

    return user;
  }
}