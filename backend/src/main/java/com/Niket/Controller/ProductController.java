package com.Niket.Controller;

import java.io.IOException;
import java.util.HexFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

  @PutMapping("/product/{prodId}")
  public ResponseEntity<String> updateProduct(@PathVariable int prodId, @RequestPart Product product,
      @RequestPart MultipartFile imageFile) throws IOException {
    Product product1 = service.updateProduct(prodId, product, imageFile);
    if (product1 != null) {
      return new ResponseEntity<>("Updated", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Failed to update product", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/product/{prodId}")
  public ResponseEntity<String> deleteProduct(@PathVariable int prodId) {
    Product product = service.deleteProduct(prodId);
    if (product != null) {
      return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Failed to delete product", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/products/search")
  public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {
    List<Product> products = service.searchProduct(keyword);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }
}
