package com.shopsphere.shopspherebackend.controller;

import com.shopsphere.shopspherebackend.entity.Product;
import com.shopsphere.shopspherebackend.repository.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://shopsphere-tau-three.vercel.app"
})
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Search products by name
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Get products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a new product
    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
    return productRepository.save(product);
}

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
    @PathVariable Long id,
    @Valid @RequestBody Product updatedProduct) {

        return productRepository.findById(id)
                .map(product -> {

                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setDescription(updatedProduct.getDescription());
                    product.setCategory(updatedProduct.getCategory());
                    product.setRating(updatedProduct.getRating());

                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}