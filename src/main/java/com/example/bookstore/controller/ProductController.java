package com.example.bookstore.controller;

import com.example.bookstore.entity.SanPham;
import com.example.bookstore.repository.ProductRepository;
import com.example.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
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

    // Tìm kiếm sản phẩm trên giao diện productList
    @GetMapping("/searchTenSP")
    public String SearchTenSP(UriComponentsBuilder uriBuilder,
                              @RequestParam("tenSP") String nameSP, @Param("tenSP") String nameSP1,
                              @RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request){
        String tensp = request.getParameter("tenSP");
        Pageable pageable = PageRequest.of(0, 5);
        List<SanPham> listProduct = productService.searchTenSP(nameSP);
        Page<SanPham> pageProduct = new PageImpl<>(listProduct, pageable, listProduct.size());
        model.addAttribute("productPage", pageProduct);
        return "productList";
    }

    // Xoá sản phẩm trên giao diện productList
    @GetMapping("/delete/{maSP}")
    public String deleteMAsp(@PathVariable("maSP") String maSP, UriComponentsBuilder uriBuilder,
                             @RequestParam(defaultValue = "0") int page, Model model) {
        boolean isDel = productService.deleteProduct(maSP);
        if (isDel == true) {
            model.addAttribute("isOk", "Xóa sản phẩm với mã " + maSP + " thành công!");
        }
        return getProducts(page, model, uriBuilder);
    }

    @ModelAttribute("PRODUCT")
    public SanPham initProduct() {
        return new SanPham();
    }

    // hiện giao diện New product
    @RequestMapping(value = "/newProduct", method = RequestMethod.GET)

    public String newPage(Model model) {
        return "ProductNew";
    }

    // Hiển thị productList sau khi tạo sản phẩm mới và hiện thông báo
    @PostMapping("/saveProduct")
    public String saveProduct(Model model, SanPham sanPham , UriComponentsBuilder uriBuilder,
                              @RequestParam(defaultValue = "0") int page) {

        if (productRepository.findByMaSP(sanPham.getMaSP())  != null){
            model.addAttribute("isUpdate", "Cập nhật sản phẩm có mã " + sanPham.getMaSP() + " thành công!!");
            productRepository.save(sanPham);
            return getProducts(page,model,uriBuilder);

        }else{
            model.addAttribute("mess", "Save product Successfully!!");
            productRepository.save(sanPham);
            return newPage(model);
        }
    }

    // chuyển sang giao diện productNew và chỉnh sửa sản phẩm khi bấm nút sửa bên productList
    @GetMapping("/update/{masp}")
    public String viewUpdate(@PathVariable("masp") String masp,Model  model){
        SanPham  sp = productRepository.findByMaSP(masp).get();
        if (sp != null){
            model.addAttribute("isUpdate", "no update id");
            model.addAttribute("PRODUCT",productRepository.findByMaSP(masp));
        }
        return "editProduct";
    }

//    @RequestMapping(value = "/newProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createProduct(@Valid @RequestBody SanPham sanPham, BindingResult result) {
//        if (result.hasErrors()) {
//            return new ResponseEntity<>(getErrors(result), HttpStatus.BAD_REQUEST);
//        } else {
//            try {
//                SanPham sanPhamMoi = new SanPham();
//                sanPhamMoi.setMaSP(sanPham.getMaSP());
//                sanPhamMoi.setTenSP(sanPham.getTenSP());
//                sanPhamMoi.setSoLuong(sanPham.getSoLuong());
//                sanPhamMoi.setDonGia(sanPham.getDonGia());
//                productRepository.save1(sanPhamMoi.getMaSP(), sanPhamMoi.getTenSP(), sanPhamMoi.getSoLuong(), sanPhamMoi.getDonGia());
//                return new ResponseEntity<>("Tạo sản phẩm mới thành công", HttpStatus.CREATED);
//            } catch (Exception e) {
//                String errorMessage = "Lỗi: " + e.getMessage();
//                return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
//            }
//        }
//    }

//    @RequestMapping(value = "/editProduct/{MaSP}", method = RequestMethod.GET)
//    public String checkProduct(Model model, @PathVariable("MaSP") String maSP) {
//        SanPham sanPham = productService.getProductByMaSP(maSP);
//        if (sanPham == null) {
//            model.addAttribute("error", "Không tìm thấy sản phẩm với mã " + maSP);
//        } else {
//            model.addAttribute("product", new SanPham(sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(), sanPham.getDonGia()));
//        }
//        return "editProduct";
//    }

//    @RequestMapping(value = "/editProduct/{MaSP}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateProduct(@PathVariable("MaSP") String MaSP, @RequestBody SanPham sanPham) {
//        SanPham updatedProduct = productRepository.save(sanPham);
//        if (updatedProduct == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    @RequestMapping(value = "/editProduct/{MaSP}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteProduct(@PathVariable("MaSP") String MaSP) {
//        boolean deleted = productService.deleteProduct(MaSP);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}