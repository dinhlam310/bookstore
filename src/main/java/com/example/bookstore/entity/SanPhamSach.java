package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SanPhamSach")
public class SanPhamSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSPSach", nullable = false)
    private String MaSPSach;

    @ManyToOne
    @JoinColumn(name = "MaDMSach")
    private String MaDMSach;


    @Column(name = "TenSPSach", nullable = false)
    private String TenSPSach;
    @Column(name = "SoLuong", nullable = false)
    private int SoLuong;
    @Column(name = "DonGia", nullable = false)
    private int DonGia;
    @Column(name = "DonVi", nullable = false)
    private int DonVi;
    @Column(name = "NhaXuatBan", nullable = false)
    private String NhaXuatBan;
    @Column(name = "TacGia", nullable = false)
    private String TacGia;
    @Column(name = "NgayXuatBan", nullable = false)
    private java.sql.Date NgayXuatBan;
    @Column(name = "LanTaiBan", nullable = false)
    private int LanTaiBan;
    @Column(name = "LinkSP")
    private String LinkSP;
}
