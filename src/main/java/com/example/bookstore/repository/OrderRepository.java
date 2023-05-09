package com.example.bookstore.repository;

import com.example.bookstore.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<DonHang, String> {
}
