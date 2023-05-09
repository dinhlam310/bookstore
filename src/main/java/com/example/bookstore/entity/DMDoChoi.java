package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "DMDoChoi")
public class DMDoChoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDMDoChoi", nullable = false)
    @NotBlank(message = "Không được để trống")
    private String MaDMDoChoi;

    @Column(name = "Nhom", nullable = false)
    private String Nhom;

    @Column(name = "MoTa", nullable = false)
    private String MoTa;
}
