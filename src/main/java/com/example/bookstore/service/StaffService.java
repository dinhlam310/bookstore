package com.example.bookstore.service;

import com.example.bookstore.entity.Staff;
import com.example.bookstore.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("StaffService")
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> searchUsername(String Username){
        return staffRepository.findByUsernameLike("%"+ Username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String tenNhanVien) throws UsernameNotFoundException {
////        NhanVien nhanVien = staffRepository.findByTenNhanVien(tenNhanVien)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
////        return new MyUserDetails(nhanVien);
//        return  null;
//    }



    // JWTAuthenticationFilter sẽ sử dụng hàm này
//    @Transactional
//    public UserDetails loadUserById(String maNhanVien) {
//        NhanVien nhanVien = staffRepository.findByMaNhanVien(maNhanVien).orElseThrow(
//                () -> new UsernameNotFoundException("User not found with id : " + maNhanVien)
//        );
//
//        return new MyUserDetails(nhanVien);
//    }

    @Transactional
    public UserDetails loadUserById(String maNhanVien) {
//        Optional<NhanVien> optionalNhanVien = staffRepository.findByMaNhanVien(maNhanVien);
//        if (optionalNhanVien.isPresent()) {
//            NhanVien nhanVien = optionalNhanVien.get();
//            return new MyUserDetails(nhanVien);
//        } else {
//            throw new UsernameNotFoundException("User not found with id : " + maNhanVien);
//        }
        return  null;
    }

    public boolean deleteStaff(String id) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent()) {
            staffRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

//    public EmployeeDTO updateStaff(String maNhanVien, EmployeeDTOO employeeDTO) {
//        Optional<NhanVien> staffOptional = staffRepository.findByMaNhanVien(maNhanVien);
//        if (staffOptional.isPresent()) {
//            NhanVien nhanVien = staffOptional.get();
//
//            nhanVien.setTenNhanVien(staffDTO.getTenNhanVien());
//            nhanVien.setEmail(staffDTO.getEmail());
//            nhanVien.setSoDienThoai(staffDTO.getSoDienThoai());
//            nhanVien.setNgaySinh(staffDTO.getNgaySinh());
//
//            staffRepository.save(nhanVien);
//
//            return convertToDTO(nhanVien);
//        } else {
//            return null;
//        }
//    }
//
//    private EmployeeDTO convertToDTO(NhanVien nhanVien) {
//        EmployeeDTO staffDTO = new EmployeeDTO();
//        employeeDTO.setMaNhanVien(nhanVien.getMaNhanVien());
//        employeeDTO.setTenNhanVien(nhanVien.getTenNhanVien());
//        employeeDTO.setEmail(nhanVien.getEmail());
//        employeeDTO.setSoDienThoai(nhanVien.getSoDienThoai());
//        return employeeDTO;
//    }
//
//    private NhanVien convertToEntity(EmployeeDTO employeeDTO) {
//        NhanVien nhanVien = new NhanVien();
//        nhanVien.setTenNhanVien(employeeDTO.getTenNhanVien());
//        nhanVien.setEmail(employeeDTO.getEmail());
//        nhanVien.setSoDienThoai(employeeDTO.getSoDienThoai());
//        return nhanVien;
//    }
}
