package com.example.bookstore.controller;

import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<?> getAllOrders() {
        // lấy danh sách đơn hàng
        // trả về danh sách đơn hàng
    }

    @PostMapping("/")
    public ResponseEntity<?>createOrder(@Valid @RequestBody Order order) {
        // tạo một đơn hàng mới
        // trả về thông tin đơn hàng mới được tạo
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(value = "id") Long orderId) {
        // lấy thông tin đơn hàng theo ID
        // trả về thông tin đơn hàng
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Order orderDetails) {
        // cập nhật thông tin đơn hàng
        // trả về thông tin đơn hàng đã được cập nhật
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
        // xóa đơn hàng
        // trả về thông tin đơn hàng đã bị xóa
        return null;
    }
}
