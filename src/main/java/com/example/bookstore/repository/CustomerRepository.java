package com.example.bookstore.repository;

import com.example.bookstore.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<KhachHang, String> {
    Page<KhachHang> findAll(Pageable pageable);

    KhachHang save(KhachHang khachHang);

    @Modifying
    @Query(value = "INSERT INTO khach_hang (ma_khach_hang, ten_khach_hang, email , so_dien_thoai , ngay_sinh , ma_loai , tong_chi_tieu) VALUES (:maKhachHang, :TenKhachHang, :Email , :SoDienThoai , :NgaySinh , :MaLoai , :TongChiTieu)", nativeQuery = true)
    void save1(@Param("maKhachHang") String maKhachHang, @Param("TenKhachHang") String TenKhachHang, @Param("Email") String Email, @Param("SoDienThoai") String SoDienThoai, @Param("NgaySinh") Date NgaySinh, @Param("LoaiKhachHang") String LoaiKhachHang, @Param("TongChiTieu") int TongChiTieu);

    Optional<KhachHang> findByMaKhachHang(String maKhachHang);

     void delete(KhachHang khachHang);
}
