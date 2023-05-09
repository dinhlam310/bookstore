package com.example.bookstore.service;

import com.example.bookstore.DAO.CustomerDAO;
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

    public boolean deleteKhachHang(String maKhachHang) {
        Optional<KhachHang> khachHangOptional = CustomerRepository.findById(maKhachHang);
        if (khachHangOptional.isPresent()) {
            CustomerRepository.delete(khachHangOptional.get());
            return true;
        } else {
            return false;
        }
    }

}
