package com.example.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
