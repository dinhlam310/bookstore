package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^KH-\\d{4}$")
    @Column(name = "MaNhanVien", nullable = false)
    @NotBlank(message = "Không được để trống")
    private String MaNhanVien;

    @Column(name = "TenNhanVien", nullable = false)
    private String TenNhanVien;
    @Column(name = "NgaySinh", nullable = false)
    private java.sql.Date NgaySinh;
    @Column(name = "SoDienThoai", nullable = false)
    @Pattern(regexp = "^(090|091|849[01])[0-9]{7}$", message = "Số điện thoại không hợp lệ")
    private String SoDienThoai;
    @Column(name = "Email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    private String Email;
    @Column(name = "DiaChi", nullable = false)
    private String DiaChi;
}
