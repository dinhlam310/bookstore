package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LoaiKhachHang")
public class LoaiKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoai", nullable = false)
    private String MaLoai;

    @Column(name = "TenLoai", nullable = false)
    private String TenLoai;

}
