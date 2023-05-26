package com.example.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MaDonHang")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private SanPham sanPham;


    @Column(name = "SoLuong", nullable = false)
    private int SoLuong;
}
