package com.example.bookstore.service;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> searchName(String Name){
        return customerRepository.findByNameLike("%"+Name);
    }

    public boolean deleteCustomer(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    // Hàm xử lý khi khách hàng mua hàng
    public void updateLevelCustomer(String maKhachHang, int amount) {
//        KhachHang khachHang = customerRepository.findByMaKhachHang(maKhachHang).orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
//
//        // Cập nhật tổng tiền các đợt mua hàng
//        khachHang.setTongChiTieu(khachHang.getTongChiTieu() + amount);
//
//        // Cập nhật cấp độ khách hàng
//        if (khachHang.getTongChiTieu() <= 10000000) {
//            khachHang.setLoaiKhachHang("Thường");
//        } else if (khachHang.getTongChiTieu() > 10000000 && khachHang.getTongChiTieu() <= 20000000) {
//            khachHang.setLoaiKhachHang("VIP1");
//        } else {
//            khachHang.setLoaiKhachHang("VIP2");
//        }
//
//        customerRepository.save(khachHang);
    }

}
