package com.example.pal.service;

import com.example.pal.dto.CreateProductDTO;
import com.example.pal.dto.ProductDTO;
import com.example.pal.dto.UpdateProductDTO;
import com.example.pal.model.Category;
import com.example.pal.model.Product;
import com.example.pal.repository.CategoryRepository;
import com.example.pal.repository.ProductRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired private ProductRepository productRepository;

  @Autowired private CategoryRepository categoryRepository;

  @Autowired private ModelMapper modelMapper;

  public ProductDTO createProduct(CreateProductDTO productDTO) {
    Product product = new Product();
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());

    Set<Category> categories = new HashSet<>();
    for (String categoryName : productDTO.getCategories()) {
      Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
      Category category =
          categoryOpt.orElseGet(
              () -> {
                Category newCategory = new Category();
                newCategory.setName(categoryName);
                return categoryRepository.save(newCategory);
              });
      categories.add(category);
    }

    product.setCategories(categories);
    Product savedProduct = productRepository.save(product);
    return modelMapper.map(savedProduct, ProductDTO.class);
  }

  // metodo getAllProducts
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }

    // metodo getProductById
    public Optional<ProductDTO> getProductById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    // metodo updateProduct

    public ProductDTO updateProduct(Long id, UpdateProductDTO productDetails) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            return null;
        }
        Product product = productOpt.get();
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        Set<Category> categories = new HashSet<>();
        for (String categoryName : productDetails.getCategories()) {
            Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
            Category category =
                categoryOpt.orElseGet(
                    () -> {
                        Category newCategory = new Category();
                        newCategory.setName(categoryName);
                        return categoryRepository.save(newCategory);
                    });
            categories.add(category);
        }

        product.setCategories(categories);
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    // metodo deleteProduct
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}