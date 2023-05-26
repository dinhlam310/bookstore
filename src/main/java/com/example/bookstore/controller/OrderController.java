package com.example.bookstore.controller;

import com.example.bookstore.entity.DonHang;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.CustomerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/page/{page}")
    public String getOrders(@PathVariable int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("maDonHang").ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, 5, sort);
        Page<DonHang> orderPage = orderRepository.findAll(pageRequest);

        model.addAttribute("orderPage",orderPage);

        String previousUrl = uriBuilder.path("/page/{page}").buildAndExpand(page - 1).toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/page/{page}").buildAndExpand(page + 1).toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "OrderList";
    }

    @GetMapping("/{maDonHang}")
    public ResponseEntity<DonHang> getOrderByMaDonHang(@PathVariable(value = "maDonHang") String maDonHang)
            throws ResourceNotFoundException {
        DonHang donHang = orderRepository.findByMaDonHang(maDonHang)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng với mã đơn hàng: " + maDonHang));
        return ResponseEntity.ok().body(donHang);
    }

    @PostMapping("/newOrder")
    public DonHang createOrder(@Valid @RequestBody DonHang donHang) {
        DonHang newOrder = orderRepository.save(donHang);
        // Cập nhật thông tin khách hàng sau khi tạo đơn hàng mới
        customerService.updateLevelCustomer(newOrder.getKhachHang().getMaKhachHang(), newOrder.getTongTien());
        return newOrder;
    }

}
