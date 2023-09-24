package com.example.bookstore.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String TenKhachHang;
    private String Email;
    private String SoDienThoai;
    private java.sql.Date NgaySinh;
    private String LoaiKhachHang;
    private int TongChiTieu;

}
