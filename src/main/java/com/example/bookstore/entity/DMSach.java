package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "DMSach")
public class DMSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDMSach", nullable = false)
    @NotBlank(message = "Không được để trống")
    private String MaDMSach;

    @Column(name = "TheLoai", nullable = false)
    private String TheLoai;
    @Column(name = "MoTa", nullable = false)
    private String MoTa;
}

