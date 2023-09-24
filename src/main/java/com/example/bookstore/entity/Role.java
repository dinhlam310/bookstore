package com.example.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 50)
    private String name;
}
