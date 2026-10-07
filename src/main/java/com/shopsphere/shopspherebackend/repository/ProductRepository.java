package com.shopsphere.shopspherebackend.repository;

import com.shopsphere.shopspherebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find products by category
    List<Product> findByCategoryIgnoreCase(String category);
}