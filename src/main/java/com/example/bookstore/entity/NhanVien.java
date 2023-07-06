package com.example.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^KH-\\d{4}$")
    @Column(name = "MaNhanVien", nullable = false)
    @NotBlank(message = "Không được để trống")
    private String maNhanVien;

    @Column(name = "MatKhau", nullable = false)
    private String MatKhau;
    @Column(name = "TenNhanVien", nullable = false)
    private String tenNhanVien;
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

    @Column(name = "Encryted_Password", length = 128)
    private String encrytedPassword;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String RoleName;
}
