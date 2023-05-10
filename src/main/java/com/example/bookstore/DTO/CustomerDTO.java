package com.example.bookstore.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private String maKhachHang;
    private String TenKhachHang;
    private String Email;
    private String SoDienThoai;
    private java.sql.Date NgaySinh;

}
