package com.Niket.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Niket.Model.Product;
import com.Niket.service.productService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

  @Autowired
  productService service;

  @GetMapping("/products")
  public List<Product> getProducts() {
    return service.getProducts();
  }

}
