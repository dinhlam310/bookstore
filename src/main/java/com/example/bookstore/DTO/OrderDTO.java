package com.example.bookstore.DTO;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Staff;
import lombok.Data;

@Data
public class OrderDTO {
    private Staff staff;
    private Customer customer;
    private java.sql.Date NgayMuaHang;
    private int TongTien;
}
