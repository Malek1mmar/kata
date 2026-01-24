package com.kata.demo.infrastructure.adapters.out.persistence;

import com.kata.demo.domain.model.InventoryStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String code;
  private String name;
  private String description;
  private String image;
  private String category;
  private Double price;
  private Integer quantity;
  private String internalReference;
  private Long shellId;

  @Enumerated(EnumType.STRING)
  private InventoryStatus inventoryStatus;

  private Double rating;
  private Long createdAt;
  private Long updatedAt;

  public ProductEntity() {}

  @PrePersist
  protected void onCreate() {
    createdAt = System.currentTimeMillis();
    updatedAt = createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = System.currentTimeMillis();
  }
}