package com.example.bookstore.service;

import com.example.bookstore.DTO.CustomerDTO;
import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.entity.SanPham;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public KhachHang getCustomerByMaKhachHang(String maKhachHang) {
        Optional<KhachHang> optionalCustomer = customerRepository.findByMaKhachHang(maKhachHang);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            return null;
        }
    }

    public boolean deleteCustomer(String maKhachHang) {
        Optional<KhachHang> customerOptional = customerRepository.findByMaKhachHang(maKhachHang);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(maKhachHang);
            return true;
        } else {
            return false;
        }
    }

    public Optional<CustomerDTO>  updateCustomer(String maKhachHang, CustomerDTO customerDTO) {
        Optional<KhachHang> customerOptional = customerRepository.findByMaKhachHang(maKhachHang);
        if (customerOptional.isPresent()) {
            KhachHang khachHang = customerOptional.get();
            khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
            khachHang.setEmail(customerDTO.getEmail());
            khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
            khachHang.setNgaySinh(customerDTO.getNgaySinh());
            customerRepository.save(khachHang);

            return Optional.of(convertToDTO(khachHang));
        } else {
            return Optional.empty();
        }
    }

    public CustomerDTO createCustomer( CustomerDTO customerDTO) {
            KhachHang khachHang = new KhachHang();
//            KhachHang khachHang = customerOptional.get();
            khachHang.setMaKhachHang(customerDTO.getMaKhachHang());
            khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
            khachHang.setEmail(customerDTO.getEmail());
            khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
            khachHang.setNgaySinh(customerDTO.getNgaySinh());
            khachHang.setTongChiTieu(customerDTO.getTongChiTieu());
            khachHang.setLoaiKhachHang(customerDTO.getLoaiKhachHang());

            customerRepository.save(khachHang);

            return convertToDTO(khachHang);

    }

    private CustomerDTO convertToDTO(KhachHang khachHang) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMaKhachHang(khachHang.getMaKhachHang());
        customerDTO.setTenKhachHang(khachHang.getTenKhachHang());
        customerDTO.setEmail(khachHang.getEmail());
        customerDTO.setSoDienThoai(khachHang.getSoDienThoai());
        customerDTO.setNgaySinh(khachHang.getNgaySinh());
        customerDTO.setTongChiTieu(khachHang.getTongChiTieu());
        customerDTO.setLoaiKhachHang(khachHang.getLoaiKhachHang());
        return customerDTO;
    }

    private KhachHang convertToEntity(CustomerDTO customerDTO) {
        KhachHang khachHang = new KhachHang();
        khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
        khachHang.setEmail(customerDTO.getEmail());
        khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
        return khachHang;
    }

    // Hàm xử lý khi khách hàng mua hàng
    public void updateLevelCustomer(String maKhachHang, int amount) {
        KhachHang khachHang = customerRepository.findByMaKhachHang(maKhachHang).orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        // Cập nhật tổng tiền các đợt mua hàng
        khachHang.setTongChiTieu(khachHang.getTongChiTieu() + amount);

        // Cập nhật cấp độ khách hàng
        if (khachHang.getTongChiTieu() <= 10000000) {
            khachHang.setLoaiKhachHang("Thường");
        } else if (khachHang.getTongChiTieu() > 10000000 && khachHang.getTongChiTieu() <= 20000000) {
            khachHang.setLoaiKhachHang("VIP1");
        } else {
            khachHang.setLoaiKhachHang("VIP2");
        }

        customerRepository.save(khachHang);
    }

}
