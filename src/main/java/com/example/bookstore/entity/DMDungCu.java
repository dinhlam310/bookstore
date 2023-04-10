package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DMDungCu")
public class DMDungCu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDMDungCu", nullable = false)
    private String MaDMDungCu;

    @Column(name = "Khoi", nullable = false)
    private String Khoi;

    @Column(name = "MoTa", nullable = false)
    private String MoTa;

    public DMDungCu() {
    }

    public DMDungCu(String MaDMDungCu, String Khoi, String MoTa) {
        this.MaDMDungCu = MaDMDungCu;
        this.Khoi = Khoi;
        this.MoTa = MoTa;
    }
}

