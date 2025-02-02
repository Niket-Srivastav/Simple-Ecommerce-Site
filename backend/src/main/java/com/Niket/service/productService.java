package com.Niket.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

  public Product addproduct(Product product, MultipartFile imageFile) throws IOException {
    product.setImageName(imageFile.getOriginalFilename());
    product.setImageType(imageFile.getContentType());
    product.setImageData(imageFile.getBytes());
    repo.save(product);
    return product;
  }

  public Product updateProduct(int prodId, Product product, MultipartFile imageFile) throws IOException {
    product.setImageData(imageFile.getBytes());
    product.setImageName(imageFile.getName());
    product.setImageType(imageFile.getContentType());
    return repo.save(product);
  }

  public Product deleteProduct(int prodId) {
    Product product = repo.findById(prodId).orElse(null);
    repo.deleteById(prodId);
    return product;
  }

  public List<Product> searchProduct(String keyword) {
    return repo.searchProduct(keyword);
  }

}
