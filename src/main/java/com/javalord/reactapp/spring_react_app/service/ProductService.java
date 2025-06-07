package com.javalord.reactapp.spring_react_app.service;

import com.javalord.reactapp.spring_react_app.model.Product;
import com.javalord.reactapp.spring_react_app.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product, MultipartFile file) {
        return productRepository.save(product);
    }

    public Product findProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public List<Product> findAllProducts(String search) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search, search);
    }
}
