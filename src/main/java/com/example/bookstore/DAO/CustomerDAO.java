package com.example.bookstore.DAO;

import com.example.bookstore.entity.KhachHang;

import java.util.List;

public interface CustomerDAO {
    public void themKhachHang(KhachHang khachHang);
    public KhachHang layKhachHang(String maKhachHang);
    public void capNhatKhachHang(KhachHang khachHang);
    public void xoaKhachHang(String maKhachHang);

    List<KhachHang> getAllKhachHang();

    void saveOrUpdateKhachHang(KhachHang khachHang);

    KhachHang getKhachHangById(Long id);

    void deleteKhachHang(Long id);
}
