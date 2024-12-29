package com.Niket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Niket.Model.Product;
import com.Niket.repo.ProductRepo;

@Service
public class productService {

  @Autowired
  ProductRepo repo;

  public List<Product> getProducts() {
    return repo.findAll();
  }

  public Product getProductbyId(int prodId) {
    return repo.findById(prodId).orElse(null);
  }

}
