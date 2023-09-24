package com.example.bookstore.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class StaffDTO {
    private String id;
    private String username;
    private Date date;
    private String phone;
    private String email;
    private String address;

}
