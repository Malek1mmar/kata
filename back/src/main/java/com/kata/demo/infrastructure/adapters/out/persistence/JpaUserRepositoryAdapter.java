package com.kata.demo.infrastructure.adapters.out.persistence;

import com.kata.demo.domain.model.User;
import com.kata.demo.domain.ports.out.UserRepositoryPort;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
  private final SpringDataUserRepository repository;

  public JpaUserRepositoryAdapter(SpringDataUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User save(User domainUser) {
    UserEntity entity = new UserEntity(
        null,
        domainUser.getUsername(),
        domainUser.getFirstname(),
        domainUser.getEmail(),
        domainUser.getPassword()
    );

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
        .map(e -> new User(e.getId(), e.getUsername(), e.getFirstname(), e.getEmail(), e.getPassword()));
  }
}