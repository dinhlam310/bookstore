package com.example.bookstore.repository;

import com.example.bookstore.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<KhachHang, String> {
//    List<KhachHang> findAllByMaKhachHang(KhachHang khachHang);

    List<KhachHang> findAll();

    KhachHang save(KhachHang khachHang);

    Optional<KhachHang> findByMaKhachHang(String maKhachHang);
}
