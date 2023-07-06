package com.example.bookstore.repository;

import com.example.bookstore.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<SanPham, String> {
    Page<SanPham> findAll(Pageable pageable);


    SanPham save(SanPham sanPham);


//    @Query(value = "INSERT IGNORE INTO SanPham (MaSP, TenSP, SoLuong, DonGia) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
//    SanPham save1(SanPham sanPham);

    @Modifying
    @Query(value = "INSERT IGNORE INTO SanPham (MaSP, TenSP, SoLuong, DonGia) VALUES (:maSP, :TenSP, :SoLuong, :DonGia)", nativeQuery = true)
    void save1(@Param("maSP") String maSP, @Param("TenSP") String TenSP, @Param("SoLuong") int SoLuong, @Param("DonGia") int DonGia);

    Optional<SanPham> findByMaSP(String maSP);
//    Optional<SanPham> findById(String maSP);

    void delete(SanPham sanPham);
}
