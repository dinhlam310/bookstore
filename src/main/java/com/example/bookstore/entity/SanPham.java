package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "san_pham")
public class SanPham {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSP", nullable = false)
    @Pattern(regexp = "^[0-9]{4}$")
    @JsonProperty("MaSP")
    private String maSP;

    @Column(name = "TenSP", nullable = false)
    @JsonProperty("TenSP")
    private String TenSP;
    @Column(name = "SoLuong", nullable = false)
    @JsonProperty("SoLuong")
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int SoLuong;
    @Column(name = "DonGia", nullable = false)
    @JsonProperty("DonGia")
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int DonGia;
}
