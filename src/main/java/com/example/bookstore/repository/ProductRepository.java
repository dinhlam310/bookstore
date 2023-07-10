package com.example.bookstore.repository;

import com.example.bookstore.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<SanPham, String> {

    Page<SanPham> findAll(Pageable pageable);

    @Query("SELECT s FROM SanPham s WHERE s.TenSP LIKE :name")
    List<SanPham> findByTenSPLike(@Param("name") String TenSP);
    SanPham save(SanPham sanPham);

    @Modifying
    @Query(value = "INSERT INTO san_pham (maSP, tenSP, so_luong, don_gia) VALUES (:maSP, :TenSP, :SoLuong, :DonGia)", nativeQuery = true)
    void save1(@Param("maSP") String maSP, @Param("TenSP") String TenSP, @Param("SoLuong") int SoLuong, @Param("DonGia") int DonGia);

    Optional<SanPham> findByMaSP(String maSP);

    void delete(SanPham sanPham);
}