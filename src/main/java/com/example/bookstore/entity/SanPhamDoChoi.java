package com.example.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "SanPhamDoChoi")
public class SanPhamDoChoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSPDoChoi", nullable = false)
    @Pattern(regexp = "^DC-\\d{4}$")
    private String MaSPDoChoi;

    @ManyToOne
    @JoinColumn(name = "MaDMDoChoi", referencedColumnName = "MaDMDoChoi")
    private DMDoChoi dmDoChoi;

    @Column(name = "TenSPDoChoi", nullable = false)
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
//    @Column(name = "HuongDan", nullable = false)
//    private int HuongDan;
//    @Column(name = "LinkSP")
//    private String LinkSP;

}
