package com.example.bookstore.DAO;

import com.example.bookstore.entity.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CustomerDAOImpl implements CustomerDAO {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void themKhachHang(KhachHang khachHang) {
//        String sql = "INSERT INTO khach_hang (ho_ten, email, so_dien_thoai) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, khachHang.getTenKhachHang(), khachHang.getEmail(), khachHang.getSoDienThoai());
//    }
//
//    @Override
//    public KhachHang layKhachHang(String maKhachHang) {
//        String sql = "SELECT * FROM khach_hang WHERE id = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{maKhachHang}, new KhachHangMapper());
//    }
//
//    @Override
//    public void capNhatKhachHang(KhachHang khachHang) {
//        String sql = "UPDATE khach_hang SET ho_ten = ?, email = ?, so_dien_thoai = ? WHERE id = ?";
//        jdbcTemplate.update(sql, khachHang.getTenKhachHang(), khachHang.getEmail(), khachHang.getSoDienThoai(), khachHang.getMaKhachHang());
//    }
//
//    @Override
//    public void xoaKhachHang(String maKhachHang) {
//        String sql = "DELETE FROM khach_hang WHERE id = ?";
//        jdbcTemplate.update(sql, maKhachHang);
//    }
}