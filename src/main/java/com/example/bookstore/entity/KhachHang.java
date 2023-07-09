package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @Pattern(regexp = "^KH-\\d{4}$")
    @Column(name = "MaKhachHang", nullable = false)
    @JsonProperty("MaKhachHang")
    private String maKhachHang;

    @Column(name = "TenKhachHang", nullable = false)
    @JsonProperty("TenKhachHang")
    private String TenKhachHang;
    @Column(name = "Email", nullable = false)
    @JsonProperty("Email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    private String Email;
    @Column(name = "SoDienThoai", nullable = false)
    @JsonProperty("SoDienThoai")
    @Pattern(regexp = "^(090|091|849[01])[0-9]{7}$", message = "So dien thoai không hợp lệ")
    private String SoDienThoai;
    @Column(name = "NgaySinh", nullable = false)
    @JsonProperty("NgaySinh")
    private java.sql.Date NgaySinh;

    @Column(name = "MaLoai", nullable = false)
    @JsonProperty("LoaiKhachHang")
    private String LoaiKhachHang;

    @Column(name = "TongChiTieu", nullable = false)
    @JsonProperty("TongChiTieu")
    private int TongChiTieu;

}
