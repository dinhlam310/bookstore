package com.example.bookstore.repository;

import com.example.bookstore.entity.Staff;
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
public interface StaffRepository extends JpaRepository<Staff, String> {
    Page<Staff> findAll(Pageable pageable);
    Optional<Staff> findById(String id);

    @Query("SELECT s FROM Staff s WHERE s.username LIKE :name")
    List<Staff> findByUsernameLike(@Param("name") String username);

    Staff findByUsername(String username);


//    @Query("SELECT ur.roles FROM Staff ur WHERE ur.roles = :userId")
//    List<String> getRoleNames(@Param("userId") String userId);

    // Trong MyUserDetail , staff su dung .getRoles, hay la viet ham ben trong staff luon ?
//    @Query("SELECT r.name FROM Staff s " +
//            "JOIN users_roles ur ON s.id = ur.user_id " +
//            "JOIN Role r ON r.id = ur.role_id " +
//            "WHERE s.id = :staffId")
//    List<String> getRoleNames(@Param("staffId") Long staffId);
}
