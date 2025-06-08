package com.javalord.reactapp.spring_react_app.service;

import com.javalord.reactapp.spring_react_app.model.Product;
import com.javalord.reactapp.spring_react_app.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private FileService fileService;

    public ProductService(ProductRepository productRepository, FileService fileService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
    }

    public Product createProduct(Product product, MultipartFile file) throws IOException{
        String stringBytes = Base64.getEncoder().encodeToString(file.getBytes());
        String image = "data:image/png;base64," + stringBytes;
        product.setImage(image);

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
