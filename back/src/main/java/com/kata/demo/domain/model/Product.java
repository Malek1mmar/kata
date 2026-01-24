package com.kata.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
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
  private InventoryStatus inventoryStatus;
  private Double rating;
  private Long createdAt;
  private Long updatedAt;

  public Product() {}

  public Product(Long id, String code, String name, String description, String image,
      String category, Double price, Integer quantity, String internalReference,
      Long shellId, InventoryStatus inventoryStatus, Double rating,
      Long createdAt, Long updatedAt) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
    this.image = image;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
    this.internalReference = internalReference;
    this.shellId = shellId;
    this.inventoryStatus = inventoryStatus;
    this.rating = rating;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}