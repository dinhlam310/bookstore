package com.example.bookstore.controller;

import com.example.bookstore.DTO.AjaxResponseBody;
import com.example.bookstore.DTO.ProductDTO;
import com.example.bookstore.entity.SanPham;
import com.example.bookstore.repository.ProductRepository;
import com.example.bookstore.service.ProductService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "productList/page", method = RequestMethod.GET)
    public String getProducts(@RequestParam(defaultValue = "0") int page, Model model, UriComponentsBuilder uriBuilder) {

        Sort sort = Sort.by("MaSP").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);
        Page<SanPham> productPage = productRepository.findAll(pageRequest);

        model.addAttribute("productPage", productPage);

        String previousUrl = uriBuilder.path("/productList/page").queryParam("page", page - 1).build().toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/productList/page").queryParam("page", page + 1).build().toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "productList";
    }

    @RequestMapping(value = "/productList/productUpdate", method = RequestMethod.GET)
    public String update() {
        return "productUpdate";
    }

    @GetMapping("/{maSP}")
    public ResponseEntity<SanPham> getCustomerByMaSP(@PathVariable(value = "maSP") String maSP)
            throws ResourceNotFoundException {
        SanPham sanPham = productRepository.findByMaSP(maSP)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm với mã sản phẩm: " + maSP));
        return ResponseEntity.ok().body(sanPham);
    }

    @RequestMapping(value = "/newProduct", method = RequestMethod.GET)
    public String newPage() {

        return "productNew";
    }

//    @PostMapping("/newProduct")
//    public ResponseEntity<?> getSearchResultViaAjax(
//            @Valid @RequestBody SanPham sanpham, Errors errors) {
//
//        AjaxResponseBody result = new AjaxResponseBody();
//
//        //If error, just return a 400 bad request, along with the error message
//        if (errors.hasErrors()) {
//
//            result.setMess(errors.getAllErrors()
//                    .stream().map(x -> x.getDefaultMessage())
//                    .collect(Collectors.joining(",")));
//
//            return ResponseEntity.badRequest().body(result);
//
//        }
//
//        SanPham sp = productRepository.save(sanpham);
//        if (sp == null) {
//            result.setMess("no user found!");
//        } else {
//            result.setMess("success");
//        }
//        result.setData(sp);
//
//        return ResponseEntity.ok(result);
//
//    }
    @RequestMapping(value = "/newProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@Valid @RequestBody SanPham sanPham, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getErrors(result), HttpStatus.BAD_REQUEST);
        } else {
            try {
                SanPham sanPhamMoi = new SanPham();
                sanPhamMoi.setMaSP(sanPham.getMaSP());
                sanPhamMoi.setTenSP(sanPham.getTenSP());
                sanPhamMoi.setSoLuong(sanPham.getSoLuong());
                sanPhamMoi.setDonGia(sanPham.getDonGia());
                productRepository.save1(sanPhamMoi.getMaSP(), sanPhamMoi.getTenSP(), sanPhamMoi.getSoLuong(), sanPhamMoi.getDonGia());
                return new ResponseEntity<>("Tạo sản phẩm mới thành công", HttpStatus.CREATED);
            } catch (Exception e) {
                String errorMessage = "Lỗi: " + e.getMessage();
                return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
            }
        }
    }

    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return errors;

    }

    @GetMapping("/editProduct")
    public String showProductForm() {
        return "editProduct";
    }

    @RequestMapping(value = "/editProduct/{MaSP}", method = RequestMethod.GET)
    public String checkProduct(Model model, @PathVariable("MaSP") String maSP) {
        SanPham sanPham = productService.getProductByMaSP(maSP);
        if (sanPham == null) {
            model.addAttribute("error", "Không tìm thấy sản phẩm với mã " + maSP);
        } else {
            model.addAttribute("product", new SanPham(sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(), sanPham.getDonGia()));
        }
        return "editProduct";
    }

    @RequestMapping(value = "/editProduct/{MaSP}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable("MaSP") String MaSP, @RequestBody SanPham sanPham) {
        SanPham updatedProduct = productRepository.save(sanPham);
        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @RequestMapping(value = "/editProduct/{MaSP}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable("MaSP") String MaSP) {
        boolean deleted = productService.deleteProduct(MaSP);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}