package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ChiTietDonHang")
public class ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MaDonHang")
    private DonHang MaDonHang;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPhamSach sanPhamSach;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPhamDoChoi sanPhamDoChoi;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPhamDungCu sanPhamDungCu;

    @Column(name = "SoLuong", nullable = false)
    private int SoLuong;
}
