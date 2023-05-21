package com.example.bookstore.DTO;

import lombok.Data;

@Data
public class StaffDTO {
    private String maNhanVien;
    private String TenNhanVien;
    private java.sql.Date NgaySinh;
    private String SoDienThoai;
    private String Email;
    private String DiaChi;

}
