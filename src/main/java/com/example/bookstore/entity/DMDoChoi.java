package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DMDoChoi")
public class DMDoChoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDMDoChoi", nullable = false)
    private String MaDMDoChoi;

    @Column(name = "Nhom", nullable = false)
    private String Nhom;

    @Column(name = "MoTa", nullable = false)
    private String MoTa;

    public DMDoChoi() {
    }

    public DMDoChoi(String MaDMDoChoi, String Nhom, String MoTa) {
        this.MaDMDoChoi = MaDMDoChoi;
        this.Nhom = Nhom;
        this.MoTa = MoTa;
    }
}
