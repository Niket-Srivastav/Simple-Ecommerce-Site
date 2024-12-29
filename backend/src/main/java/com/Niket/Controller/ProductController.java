package com.Niket.Controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<List<Product>> getProducts() {
    return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
  }

  @GetMapping("/product/{prodId}")
  public ResponseEntity<Product> getProductbyId(@PathVariable int prodId) {
    Product res = service.getProductbyId(prodId);

    if (res != null) {
      return new ResponseEntity<>(res, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
