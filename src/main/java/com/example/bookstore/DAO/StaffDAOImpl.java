package com.example.bookstore.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public abstract class StaffDAOImpl implements StaffDAO{
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Override
//    public void themNhanVien(NhanVien nhanVien) {
//        String sql = "INSERT INTO khach_hang (ho_ten, email, so_dien_thoai) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, nhanVien.getTenNhanVien(), nhanVien.getEmail(), nhanVien.getSoDienThoai());
//    }
//
//    @Override
//    public NhanVien layNhanVien(String maNhanVien) {
//        String sql = "SELECT * FROM khach_hang WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{maNhanVien}, new KhachHangMapper());
//    }
//
//    @Override
//    public void capNhatNhanVien(NhanVien nhanVien) {
//        String sql = "UPDATE khach_hang SET ho_ten = ?, email = ?, so_dien_thoai = ? WHERE id = ?";
//        jdbcTemplate.update(sql, nhanVien.getTenNhanVien(), nhanVien.getEmail(), nhanVien.getSoDienThoai(), nhanVien.getMaNhanVien());
//    }
//
//    @Override
//    public void xoaNhanVien(String maNhanVien) {
//        String sql = "DELETE FROM khach_hang WHERE id = ?";
//        jdbcTemplate.update(sql, maNhanVien);
//    }


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.nhanVien.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.appUser.userId = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
