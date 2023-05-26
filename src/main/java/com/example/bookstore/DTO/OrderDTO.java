package com.example.bookstore.DTO;

import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.entity.NhanVien;
import lombok.Data;

@Data
public class OrderDTO {
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private java.sql.Date NgayMuaHang;
    private int TongTien;
}
