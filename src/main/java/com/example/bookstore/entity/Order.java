package com.example.bookstore.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "total", nullable = false)
    @Min(value = 0, message = "Giá trị phải lớn hơn hoặc bằng 0")
    private int total;

    @Column(name = "date_buy", nullable = false)
    private java.sql.Date dateBuy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_staff", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    private Set<Staff> staff = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_customers", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customer = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public int calculateTotal() {
        int total = 0;
        for (OrderDetail orderDetail : orderDetails) {
            int quantity = orderDetail.getQuantity();
            int price = orderDetail.getProduct().getPrice();
            total += quantity * price;
        }
        this.setTotal(total);
        return total;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
