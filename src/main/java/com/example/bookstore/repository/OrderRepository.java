package com.example.bookstore.repository;

import com.example.bookstore.entity.DonHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<DonHang, String> {

    Page<DonHang> findAll(Pageable pageable);

    Optional<DonHang> findByMaDonHang(String MaDonHang);
    Optional<DonHang> findById(String MaDonHang);

}