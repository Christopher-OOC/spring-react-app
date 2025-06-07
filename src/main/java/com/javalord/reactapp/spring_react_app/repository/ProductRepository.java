package com.javalord.reactapp.spring_react_app.repository;

import com.javalord.reactapp.spring_react_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String search1, String search2);


}
