package com.example.bookstore.service;

import com.example.bookstore.DTO.StaffDTO;
import com.example.bookstore.details.CustomUserDetails;
import com.example.bookstore.entity.NhanVien;
import com.example.bookstore.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("StaffService")
public class StaffService implements UserDetailsService {
//    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String tenNhanVien) throws UsernameNotFoundException {
        NhanVien nhanVien = staffRepository.findByTenNhanVien(tenNhanVien)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(nhanVien);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
//    @Transactional
//    public UserDetails loadUserById(String maNhanVien) {
//        NhanVien nhanVien = staffRepository.findByMaNhanVien(maNhanVien).orElseThrow(
//                () -> new UsernameNotFoundException("User not found with id : " + maNhanVien)
//        );
//
//        return new CustomUserDetails(nhanVien);
//    }

    @Transactional
    public UserDetails loadUserById(String maNhanVien) {
        Optional<NhanVien> optionalNhanVien = staffRepository.findByMaNhanVien(maNhanVien);
        if (optionalNhanVien.isPresent()) {
            NhanVien nhanVien = optionalNhanVien.get();
            return new CustomUserDetails(nhanVien);
        } else {
            throw new UsernameNotFoundException("User not found with id : " + maNhanVien);
        }
    }

    public boolean deleteStaff(String maNhanVien) {
        Optional<NhanVien> staffOptional = staffRepository.findByMaNhanVien(maNhanVien);
        if (staffOptional.isPresent()) {
            staffRepository.deleteById(maNhanVien);
            return true;
        } else {
            return false;
        }
    }

    public StaffDTO updateStaff(String maNhanVien, StaffDTO staffDTO) {
        Optional<NhanVien> staffOptional = staffRepository.findByMaNhanVien(maNhanVien);
        if (staffOptional.isPresent()) {
            NhanVien nhanVien = staffOptional.get();

            nhanVien.setTenNhanVien(staffDTO.getTenNhanVien());
            nhanVien.setEmail(staffDTO.getEmail());
            nhanVien.setSoDienThoai(staffDTO.getSoDienThoai());
            nhanVien.setNgaySinh(staffDTO.getNgaySinh());

            staffRepository.save(nhanVien);

            return convertToDTO(nhanVien);
        } else {
            return null;
        }
    }

    private StaffDTO convertToDTO(NhanVien nhanVien) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setMaNhanVien(nhanVien.getMaNhanVien());
        staffDTO.setTenNhanVien(nhanVien.getTenNhanVien());
        staffDTO.setEmail(nhanVien.getEmail());
        staffDTO.setSoDienThoai(nhanVien.getSoDienThoai());
        return staffDTO;
    }

    private NhanVien convertToEntity(StaffDTO staffDTO) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setTenNhanVien(staffDTO.getTenNhanVien());
        nhanVien.setEmail(staffDTO.getEmail());
        nhanVien.setSoDienThoai(staffDTO.getSoDienThoai());
        return nhanVien;
    }
}
