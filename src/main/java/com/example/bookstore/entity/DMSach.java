package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DMSach")
public class DMSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDMSach", nullable = false)
    private String MaDMSach;

    @Column(name = "TheLoai", nullable = false)
    private String TheLoai;

    @Column(name = "MoTa", nullable = false)
    private String MoTa;

    public DMSach() {
    }

    public DMSach(String MaDMSach, String TheLoai, String MoTa) {
        this.MaDMSach = MaDMSach;
        this.TheLoai = TheLoai;
        this.MoTa = MoTa;
    }
}

