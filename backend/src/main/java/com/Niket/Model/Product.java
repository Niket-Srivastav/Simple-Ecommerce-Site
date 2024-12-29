package com.Niket.Model;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
  private int id;
  private String name;
  private String desc;
  private String brand;
  private BigDecimal price;
  private String category;
  private Date releaseDate;
  private Boolean available;
  private int quantity;
}
