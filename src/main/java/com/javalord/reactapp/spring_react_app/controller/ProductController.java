package com.javalord.reactapp.spring_react_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalord.reactapp.spring_react_app.model.Product;
import com.javalord.reactapp.spring_react_app.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createProduct(
            @RequestParam("data") String product,
            @RequestParam(value = "file", required = false) MultipartFile file
            ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product p = objectMapper.readValue(product, Product.class);

        System.out.println("File name: " + file.getOriginalFilename());

        Product returnValue = productService.createProduct(p, file);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable("productId") int productId) {
        Product product = productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<?> findAllProducts(
            @RequestParam(value = "search", defaultValue = "", required = false) String search
            ) throws InterruptedException {

        Thread.sleep(3000);

        List<Product> products = productService.findAllProducts(search);
        return ResponseEntity.ok(products);
    }

}
