package com.example.bookstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "SanPham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSP", nullable = false)
    @Pattern(regexp = "^S-\\d{4}$")
    private String MaSP;

    @Column(name = "TenSP", nullable = false)
    private String TenSP;
    @Column(name = "SoLuong", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int SoLuong;
    @Column(name = "DonGia", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int DonGia;
}
