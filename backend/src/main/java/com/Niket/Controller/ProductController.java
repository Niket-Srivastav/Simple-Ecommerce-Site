package com.Niket.Controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Niket.Model.Product;
import com.Niket.service.productService;

import jakarta.annotation.PostConstruct;

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

  @PostMapping("/product")
  public ResponseEntity<?> addproduct(@RequestPart Product product,
      @RequestPart MultipartFile imageFile) {
    try {
      Product res = service.addproduct(product, imageFile);
      return new ResponseEntity<>(res, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/product/{prodId}/image")
  public ResponseEntity<byte[]> getImage(@PathVariable int prodId) {
    Product product = service.getProductbyId(prodId);
    byte[] imageFile = product.getImageData();
    return new ResponseEntity<>(imageFile, HttpStatus.OK);
  }
}
