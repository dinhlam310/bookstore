package com.example.bookstore.DAO;

import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.entity.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
