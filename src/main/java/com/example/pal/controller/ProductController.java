package com.example.pal.controller;

import com.example.pal.dto.CreateProductDTO;
import com.example.pal.dto.ProductDTO;
import com.example.pal.dto.UpdateProductDTO;
import com.example.pal.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired private ProductService productService;

  @PostMapping("/create")
  public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO productDTO) {
    ProductDTO product = productService.createProduct(productDTO);
    return ResponseEntity.status(201).body(product);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    Optional<ProductDTO> product = productService.getProductById(id);
    return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ProductDTO> updateProduct(
      @PathVariable Long id, @RequestBody UpdateProductDTO productDetails) {
    ProductDTO updatedProduct = productService.updateProduct(id, productDetails);
    return ResponseEntity.ok(updatedProduct);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}