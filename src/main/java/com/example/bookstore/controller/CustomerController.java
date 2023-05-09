package com.example.bookstore.controller;

import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.service.CustomerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/List")
    public List<KhachHang> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/New")
    public KhachHang createCustomer(@Valid @RequestBody KhachHang khachHang) {
        return customerRepository.save(khachHang);
    }

    @GetMapping("/customers/{maKhachHang}")
    public ResponseEntity<KhachHang> getCustomerByMaKhachHang(@PathVariable(value = "maKhachHang") String maKhachHang)
            throws ResourceNotFoundException {
        KhachHang khachHang = customerRepository.findByMaKhachHang(maKhachHang)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với mã khách hàng: " + maKhachHang));
        return ResponseEntity.ok().body(khachHang);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<KhachHang> updateCustomer(@PathVariable(value = "maKhachHang") String maKhachHang, @Valid @RequestBody KhachHang customerDetails) throws ResourceNotFoundException {
        KhachHang khachHang = customerRepository.findById(maKhachHang)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + maKhachHang));

        // Cập nhật thông tin khách hàng
        khachHang.setTenKhachHang(customerDetails.getTenKhachHang());
        khachHang.setEmail(customerDetails.getEmail());
        khachHang.setSoDienThoai(customerDetails.getSoDienThoai());
        // Cập nhật các trường khác tùy theo yêu cầu của bạn

        final KhachHang updatedCustomer = customerRepository.save(khachHang);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String maKhachHang) {
        // Thực hiện xóa khách hàng với ID được truyền vào , CustomerService là 1 phương thức tự viết bên trong folder Service
        boolean daXoa = CustomerService.deleteKhachHang(maKhachHang);

        // Nếu khách hàng đã được xóa thành công, trả về phản hồi HTTP 204 No Content
        if (daXoa) {
            return ResponseEntity.noContent().build();
        }
        // Nếu không, trả về phản hồi HTTP 404 Not Found
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
