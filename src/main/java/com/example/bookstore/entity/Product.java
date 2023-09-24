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
@Table(name = "product")
public class Product {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Pattern(regexp = "^[0-9]{4}$")
    //@JsonProperty("id")
    private String id;

    @Column(name = "name", nullable = false)
    //@JsonProperty("name")
    private String name;
    @Column(name = "quantity", nullable = false)
    //@JsonProperty("quantity")
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int quantity;
    @Column(name = "price", nullable = false)
    //@JsonProperty("price")
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int price;
}
