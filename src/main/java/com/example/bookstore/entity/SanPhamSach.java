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
@Table(name = "SanPhamSach")
public class SanPhamSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSPSach", nullable = false)
    @Pattern(regexp = "^S-\\d{4}$")
    private String MaSPSach;

    @ManyToOne
    @JoinColumn(name = "MaDMSach", referencedColumnName = "MaDMSach")
    private DMSach dmSach;

    @Column(name = "TenSPSach", nullable = false)
    private String TenSPSach;
    @Column(name = "SoLuong", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int SoLuong;
    @Column(name = "DonGia", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int DonGia;

//    @Column(name = "DonVi", nullable = false)
//    private int DonVi;
//    @Column(name = "NhaXuatBan", nullable = false)
//    private String NhaXuatBan;
//    @Column(name = "TacGia", nullable = false)
//    private String TacGia;
//    @Column(name = "NgayXuatBan", nullable = false)
//    private java.sql.Date NgayXuatBan;
//    @Column(name = "LanTaiBan", nullable = false)
//    @Min(value = 1, message = "Giá trị phải lớn hơn hoặc bằng 1")
//    private int LanTaiBan;
//    @Column(name = "LinkSP")
//    private String LinkSP;

}
