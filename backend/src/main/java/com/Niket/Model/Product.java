package com.Niket.Model;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data // provides getter and setter 
@AllArgsConstructor // all argument constructor
@NoArgsConstructor // No argument constructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private int id;
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private String category;
  private Date releaseDate;
  private Boolean productAvailable;
  private int stockQuantity;

  private String imageName;
  private String imageType;
  @Lob
  private byte[] imageData;
}
