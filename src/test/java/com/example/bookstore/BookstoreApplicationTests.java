package com.example.bookstore;


import com.example.bookstore.entity.SanPham;
import com.example.bookstore.repository.ProductRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
        SanPham sanPham = SanPham.builder().TenSP("iphone").build();
        productRepository.save(sanPham);
    }

}
