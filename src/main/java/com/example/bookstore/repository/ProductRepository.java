package com.example.bookstore.repository;

import com.example.bookstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAll(Pageable pageable);
//
    @Query("SELECT s FROM Product s WHERE s.name LIKE :name")
    List<Product> findByNameLike(@Param("name") String name);
    Product save(Product product);
//
////    @Modifying
////    @Query(value = "INSERT INTO san_pham (maSP, tenSP, so_luong, don_gia) VALUES (:maSP, :TenSP, :SoLuong, :DonGia)", nativeQuery = true)
////    void save1(@Param("maSP") String maSP, @Param("TenSP") String TenSP, @Param("SoLuong") int SoLuong, @Param("DonGia") int DonGia);
//
    Optional<Product> findById(String id);

}