package com.example.bookstore.controller;

import com.example.bookstore.DTO.StaffDTO;
import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.entity.NhanVien;
import com.example.bookstore.repository.StaffRepository;
import com.example.bookstore.service.StaffService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/staffs")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "staffList/page", method = RequestMethod.GET)
    public String getStaffs(@RequestParam(defaultValue = "0") int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("MaNhanVien").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);
        Page<NhanVien> staffPage = staffRepository.findAll(pageRequest);

        model.addAttribute("staffPage", staffPage);

        String previousUrl = uriBuilder.path("/staffList/page").queryParam("page", page - 1).build().toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/staffList/page").queryParam("page", page + 1).build().toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "staffList";

    }

    @GetMapping("/{maNhanVien}")
    public ResponseEntity<NhanVien> getStaffByMaNhanVien(@PathVariable(value = "maNhanVien") String maNhanVien)
            throws ResourceNotFoundException {
        NhanVien nhanVien = staffRepository.findByMaNhanVien(maNhanVien)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể tìm thấy nhân viên với mã nhân viên: " + maNhanVien));
        return ResponseEntity.ok().body(nhanVien);
    }

    @PostMapping("/NewStaff")
    public NhanVien createStaff(@Valid @RequestBody NhanVien nhanVien) {
        return staffRepository.save(nhanVien);
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable("staffId") String maNhanVien, @RequestBody StaffDTO staffDTO) {
        StaffDTO updatedStaff = staffService.updateStaff(maNhanVien, staffDTO);
        if (updatedStaff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String maNhanVien) {
        boolean deleted = staffService.deleteStaff(maNhanVien);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}