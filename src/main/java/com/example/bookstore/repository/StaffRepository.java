package com.example.bookstore.repository;

import com.example.bookstore.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<NhanVien, String> {
    Page<NhanVien> findAll(Pageable pageable);

    NhanVien save(NhanVien nhanVien);

    Optional<NhanVien> findByTenNhanVien(String TenNhanVien);

    Optional<NhanVien> findByMaNhanVien(String maNhanVien);

    void delete(NhanVien nhanVien);
}
