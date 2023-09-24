package com.example.bookstore.service;

import com.example.bookstore.DTO.CustomerDTO;
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

    public Customer getCustomerByMaKhachHang(String maKhachHang) {
//        Optional<KhachHang> optionalCustomer = customerRepository.findByMaKhachHang(maKhachHang);
//        if (optionalCustomer.isPresent()) {
//            return optionalCustomer.get();
//        } else {
//            return null;
//        }
        return null;
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

    public Optional<CustomerDTO>  updateCustomer(String maKhachHang, CustomerDTO customerDTO) {
//        Optional<KhachHang> customerOptional = customerRepository.findByMaKhachHang(maKhachHang);
//        if (customerOptional.isPresent()) {
//            KhachHang khachHang = customerOptional.get();
//            khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
//            khachHang.setEmail(customerDTO.getEmail());
//            khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
//            khachHang.setNgaySinh(customerDTO.getNgaySinh());
//            customerRepository.save(khachHang);
//
//            return Optional.of(convertToDTO(khachHang));
//        } else {
//            return Optional.empty();
//        }
        return null;
    }

//    public CustomerDTO createCustomer( CustomerDTO customerDTO) {
//            Customer customer = new Customer();
////            KhachHang khachHang = customerOptional.get();
//            customer.setMaKhachHang(customerDTO.getMaKhachHang());
//            customer.setTenKhachHang(customerDTO.getTenKhachHang());
//            customer.setEmail(customerDTO.getEmail());
//            customer.setSoDienThoai(customerDTO.getSoDienThoai());
//            customer.setNgaySinh(customerDTO.getNgaySinh());
//            customer.setTongChiTieu(customerDTO.getTongChiTieu());
//            customer.setLoaiKhachHang(customerDTO.getLoaiKhachHang());
//
//            customerRepository.save(customer);
//
//            return convertToDTO(customer);
//
//    }
//
//    private CustomerDTO convertToDTO(Customer customer) {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setMaKhachHang(customer.getMaKhachHang());
//        customerDTO.setTenKhachHang(customer.getTenKhachHang());
//        customerDTO.setEmail(customer.getEmail());
//        customerDTO.setSoDienThoai(customer.getSoDienThoai());
//        customerDTO.setNgaySinh(customer.getNgaySinh());
//        customerDTO.setTongChiTieu(customer.getTongChiTieu());
//        customerDTO.setLoaiKhachHang(customer.getLoaiKhachHang());
//        return customerDTO;
//    }
//
//    private Customer convertToEntity(CustomerDTO customerDTO) {
//        Customer customer = new Customer();
//        customer.setTenKhachHang(customerDTO.getTenKhachHang());
//        customer.setEmail(customerDTO.getEmail());
//        customer.setSoDienThoai(customerDTO.getSoDienThoai());
//        return customer;
//    }

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
