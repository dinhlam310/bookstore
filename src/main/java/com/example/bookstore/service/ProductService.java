package com.example.bookstore.service;

import com.example.bookstore.entity.Product;
import com.example.bookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchName(String Name){
       return productRepository.findByNameLike("%"+ Name);
    }
    public Product getProductById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }

    public boolean deleteProduct(String Id) {
        Optional<Product> productOptional = productRepository.findById(Id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(Id);
            return true;
        } else {
            return false;
        }
    }

}
