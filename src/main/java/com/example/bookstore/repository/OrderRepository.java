package com.example.bookstore.repository;

import com.example.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    Page<Order> findAll(Pageable pageable);

    @Query("SELECT s FROM Order s WHERE s.id LIKE :id")
    List<Order> findByIdLike(@Param("id") String Id);

    Optional<Order> findById(String id);
}