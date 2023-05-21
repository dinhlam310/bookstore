package com.example.bookstore.repository;

import com.example.bookstore.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<KhachHang , String> {
}
