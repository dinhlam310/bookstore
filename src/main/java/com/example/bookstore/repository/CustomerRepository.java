package com.example.bookstore.repository;

import com.example.bookstore.entity.Customer;
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
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Page<Customer> findAll(Pageable pageable);

    @Query("SELECT s FROM Customer s WHERE s.name LIKE :name")
    List<Customer> findByNameLike(@Param("name") String Name);

    Optional<Customer> findById(String id);
}
