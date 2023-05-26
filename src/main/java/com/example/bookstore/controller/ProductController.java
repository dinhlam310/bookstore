package com.example.bookstore.controller;

import com.example.bookstore.DTO.ProductDTO;
import com.example.bookstore.entity.SanPham;
import com.example.bookstore.repository.ProductRepository;
import com.example.bookstore.service.ProductService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/page/{page}")
    public String getProducts(@PathVariable int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("MaSP").ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, 5, sort);
        Page<SanPham> productPage = productRepository.findAll(pageRequest);

        model.addAttribute("productPage", productPage);

        String previousUrl = uriBuilder.path("/page/{page}").buildAndExpand(page - 1).toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/page/{page}").buildAndExpand(page + 1).toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "ProductList";
    }

    @GetMapping("/{maSP}")
    public ResponseEntity<SanPham> getCustomerByMaSP(@PathVariable(value = "maSP") String maSP)
            throws ResourceNotFoundException {
        SanPham sanPham = productRepository.findByMaSP(maSP)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm với mã sản phẩm: " + maSP));
        return ResponseEntity.ok().body(sanPham);
    }

    @PostMapping("/newProduct")
    public SanPham createProduct(@Valid @RequestBody SanPham sanPham) {
        return productRepository.save(sanPham);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") String MaSP, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(MaSP, productDTO);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") String MaSP) {
        boolean deleted = productService.deleteProduct(MaSP);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}