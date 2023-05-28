package com.example.bookstore.repository;

import com.example.bookstore.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<SanPham, String> {
    Page<SanPham> findAll(Pageable pageable);

    SanPham save(SanPham sanPham);

    Optional<SanPham> findByMaSP(String maSP);
    Optional<SanPham> findById(String maSP);

    void delete(SanPham sanPham);
}
