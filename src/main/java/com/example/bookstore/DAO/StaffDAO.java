package com.example.bookstore.DAO;

import com.example.bookstore.entity.NhanVien;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDAO {
//    public void themNhanVien(NhanVien nhanVien);
//    public NhanVien layNhanVien(String maNhanVien);
//    public void capNhatNhanVien(NhanVien nhanVien);
//    public void xoaNhanVien(String maNhanVien);
//    List<NhanVien> getAllNhanVien();
//
//    void saveOrUpdateNhanVien(NhanVien nhanVien);
//
    NhanVien getNhanVienById(String maNhanVien);

    List<String> getRoleNames(String roleName);

//    List<String> getRoleNames(Long userId);
}
