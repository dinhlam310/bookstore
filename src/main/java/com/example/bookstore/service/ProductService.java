package com.example.bookstore.service;

import com.example.bookstore.DTO.CustomerDTO;
import com.example.bookstore.DTO.ProductDTO;
import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.entity.SanPham;
import com.example.bookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<SanPham> searchTenSP(String TenSP){
        return productRepository.findByTenSPLike("%"+TenSP);
    }
    public SanPham getProductByMaSP(String maSP) {
        Optional<SanPham> optionalProduct = productRepository.findByMaSP(maSP);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }

    public boolean deleteProduct(String MaSP) {
        Optional<SanPham> productOptional = productRepository.findByMaSP(MaSP);
        if (productOptional.isPresent()) {
            productRepository.deleteById(MaSP);
            return true;
        } else {
            return false;
        }
    }

    public  ProductDTO updateProduct(String MaSP, ProductDTO productDTO) {
        Optional<SanPham> productOptional = productRepository.findByMaSP(MaSP);
        if (productOptional.isPresent()) {
            SanPham sanPham = productOptional.get();

            sanPham.setTenSP(productDTO.getTenSP());
            sanPham.setSoLuong(productDTO.getSoLuong());
            sanPham.setDonGia(productDTO.getDonGia());

            productRepository.save(sanPham);

            return convertToDTO(sanPham);
        } else {
            return null;
        }
    }

    private ProductDTO convertToDTO(SanPham sanPham) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setTenSP(sanPham.getTenSP());
        productDTO.setSoLuong(sanPham.getSoLuong());
        productDTO.setDonGia(sanPham.getDonGia());
        return productDTO;
    }

//    private KhachHang convertToEntity(CustomerDTO customerDTO) {
//        KhachHang khachHang = new KhachHang();
//        khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
//        khachHang.setEmail(customerDTO.getEmail());
//        khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
//        return khachHang;
//    }
}
