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

  public Long getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getImage() {
    return image;
  }

  public String getCategory() {
    return category;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public String getInternalReference() {
    return internalReference;
  }

  public Long getShellId() {
    return shellId;
  }

  public InventoryStatus getInventoryStatus() {
    return inventoryStatus;
  }

  public Double getRating() {
    return rating;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setInternalReference(String internalReference) {
    this.internalReference = internalReference;
  }

  public void setShellId(Long shellId) {
    this.shellId = shellId;
  }

  public void setInventoryStatus(InventoryStatus inventoryStatus) {
    this.inventoryStatus = inventoryStatus;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }
}