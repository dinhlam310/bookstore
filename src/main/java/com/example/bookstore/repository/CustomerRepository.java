package com.example.bookstore.repository;

import com.example.bookstore.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<KhachHang, String> {
    Page<KhachHang> findAll(Pageable pageable);

    KhachHang save(KhachHang khachHang);


    Optional<KhachHang> findByMaKhachHang(String maKhachHang);
//    Optional<KhachHang> findById(String MaKhachHang);

     void delete(KhachHang khachHang);
}
