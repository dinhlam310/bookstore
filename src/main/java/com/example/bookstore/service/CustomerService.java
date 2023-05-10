package com.example.bookstore.service;

import com.example.bookstore.DAO.CustomerDAO;
import com.example.bookstore.DTO.CustomerDTO;
import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private static CustomerDAO CustomerDAO;

    @Autowired
    private CustomerRepository customerRepository;

    public List<KhachHang> getAllKhachHang() {
        return CustomerDAO.getAllKhachHang();
    }

    public KhachHang getKhachHangById(Long id) {
        return CustomerDAO.getKhachHangById(id);
    }

    public void saveOrUpdateKhachHang(KhachHang khachHang) {
        CustomerDAO.saveOrUpdateKhachHang(khachHang);
    }

//    public static void deleteKhachHang(String maKhachHang) {
//        CustomerDAO.deleteKhachHang(maKhachHang);
//    }

//    public boolean deleteKhachHang(String maKhachHang) {
//        Optional<KhachHang> khachHangOptional = customerRepository.findById(maKhachHang);
//        if (khachHangOptional.isPresent()) {
//            customerRepository.delete(khachHangOptional.get());
//            return true;
//        } else {
//            return false;
//        }
//    }

    public boolean deleteCustomer(String maKhachHang) {
        Optional<KhachHang> customerOptional = customerRepository.findById(maKhachHang);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(maKhachHang);
            return true;
        } else {
            return false;
        }
    }

    public CustomerDTO updateCustomer(String maKhachHang, CustomerDTO customerDTO) {
        Optional<KhachHang> customerOptional = customerRepository.findById(maKhachHang);
        if (customerOptional.isPresent()) {
            KhachHang khachHang = customerOptional.get();
            // cập nhật thông tin của khách hàng từ customerDTO
            khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
            khachHang.setEmail(customerDTO.getEmail());
            khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
            khachHang.setNgaySinh(customerDTO.getNgaySinh());
            // lưu thay đổi vào cơ sở dữ liệu
            customerRepository.save(khachHang);
            // trả về thông tin của khách hàng sau khi cập nhật
            return convertToDTO(khachHang);
        } else {
            return null;
        }
    }

    private CustomerDTO convertToDTO(KhachHang khachHang) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMaKhachHang(khachHang.getMaKhachHang());
        customerDTO.setTenKhachHang(khachHang.getTenKhachHang());
        customerDTO.setEmail(khachHang.getEmail());
        customerDTO.setSoDienThoai(khachHang.getSoDienThoai());
        return customerDTO;
    }

    private KhachHang convertToEntity(CustomerDTO customerDTO) {
        KhachHang khachHang = new KhachHang();
        khachHang.setTenKhachHang(customerDTO.getTenKhachHang());
        khachHang.setEmail(customerDTO.getEmail());
        khachHang.setSoDienThoai(customerDTO.getSoDienThoai());
        return khachHang;
    }

}
