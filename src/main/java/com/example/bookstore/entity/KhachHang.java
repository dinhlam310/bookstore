package com.example.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^KH-\\d{4}$") // có dạng là KH-XXXX
    @Column(name = "MaKhachHang", nullable = false)
    @NotBlank(message = "Không được để trống")
    private String maKhachHang;

    @Column(name = "TenKhachHang", nullable = false)
    private String TenKhachHang;
    @Column(name = "Email", nullable = false)
    @Pattern(regexp = "[\\w-\\.]+@[\\w-]+\\.[\\w-\\.]+", message = "Email không hợp lệ")
    private String Email;
    @Column(name = "SoDienThoai", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    private String SoDienThoai;
    @Column(name = "NgaySinh", nullable = false)
    private java.sql.Date NgaySinh;

    @Column(name = "MaLoai", nullable = false)
    private String LoaiKhachHang;

    @Column(name = "TongChiTieu", nullable = false)
    private int TongChiTieu;
}
