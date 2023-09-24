package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Pattern(regexp = "^KH-\\d{4}$")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    private String email;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "^(090|091|849[01])[0-9]{7}$", message = "So dien thoai không hợp lệ")
    private String phone;

    @Column(name = "birthday", nullable = false)
    private java.sql.Date birthday;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "billspend", nullable = false)
    private int billspend;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_customers", joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> order = new HashSet<>();

//    public int calculateTotal() {
//        int totalBillSpend = 0;
//        String customerId = this.getId();
//        for (Order currentOrder : order) {
//            int moneyPerBill = currentOrder.getTotal(currentOrder.getCustomer().add(getId().));
//            totalBillSpend += moneyPerBill;
//        }
//        return totalBillSpend;
//    }

    public int calculatebillspend() {
        int billspend = 0;
        for (Order order : order) {
            billspend += order.calculateTotal();
        }
        this.setBillspend(billspend);
        return billspend;
    }
}
