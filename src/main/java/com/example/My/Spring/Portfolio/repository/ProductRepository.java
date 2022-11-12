package com.example.My.Spring.Portfolio.repository;

import com.example.My.Spring.Portfolio.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

        boolean existsByEmail(String email);

        Product findByEmail(String email);
}

