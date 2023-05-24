package com.example.bookstore.controller;

import com.example.bookstore.repository.ProductRepository;
import com.example.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        // lấy danh sách sản phẩm
        // trả về danh sách sản phẩm
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        // tạo một sản phẩm mới
        // trả về thông tin sản phẩm mới được tạo
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long productId) {
        // lấy thông tin sản phẩm theo ID
        // trả về thông tin sản phẩm
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(value = "id") Long productId, @Valid @RequestBody Product productDetails) {
        // cập nhật thông tin sản phẩm
        // trả về thông tin sản phẩm đã được cập nhật
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
        // xóa sản phẩm
        // trả về thông tin sản phẩm đã bị xóa
    }
}