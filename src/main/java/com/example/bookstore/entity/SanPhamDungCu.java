package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "SanPhamDungCu")
public class SanPhamDungCu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSPDungCu", nullable = false)
    @Pattern(regexp = "^DC-\\d{4}$")
    private String MaSPDungCu;

    @ManyToOne
    @JoinColumn(name = "MaDMDungCu", referencedColumnName = "MaDMDungCu")
    private DMDungCu dmDungCu;

    @Column(name = "TenSPDungCu", nullable = false)
    private String TenSPSach;
    @Column(name = "SoLuong", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int SoLuong;
    @Column(name = "DonGia", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int DonGia;

//    @Column(name = "DonVi", nullable = false)
//    private int DonVi;
//    @Column(name = "XuatXu", nullable = false)
//    private String XuatXu;
//    @Column(name = "ThuongHieu", nullable = false)
//    private String ThuongHieu;
//    @Column(name = "NhaCungCap", nullable = false)
//    private String NhaCungCap;
//    @Column(name = "MauSac", nullable = false)
//    private String MauSac;
//    @Column(name = "KichThuoc", nullable = false)
//    private int KichThuoc;
//    @Column(name = "ChatLieu", nullable = false)
//    private String ChatLieu;
//    @Column(name = "HuongDan", nullable = false)
//    private String HuongDan;
//    @Column(name = "LinkSP")
//    private String LinkSP;

}
