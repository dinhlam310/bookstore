package com.example.bookstore.controller;

import com.example.bookstore.entity.Staff;
import com.example.bookstore.repository.StaffRepository;
import com.example.bookstore.service.StaffService;
//import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/staffs")
@CrossOrigin(origins = "*")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "staffList/page", method = RequestMethod.GET)
    public String getStaffs(@RequestParam(defaultValue = "0") int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("Id").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);
        Page<Staff> staffPage = staffRepository.findAll(pageRequest);

        model.addAttribute("staffPage", staffPage);

        String previousUrl = uriBuilder.path("/staffList/page").queryParam("page", page - 1).build().toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/staffList/page").queryParam("page", page + 1).build().toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "staff/staffList";

    }

//    @GetMapping("/{maNhanVien}")
//    public ResponseEntity<NhanVien> getStaffByMaNhanVien(@PathVariable(value = "maNhanVien") String maNhanVien)
//            throws ResourceNotFoundException {
//        NhanVien nhanVien = staffRepository.findByMaNhanVien(maNhanVien)
//                .orElseThrow(() -> new ResourceNotFoundException("Không thể tìm thấy nhân viên với mã nhân viên: " + maNhanVien));
//        return ResponseEntity.ok().body(nhanVien);
//    }
//
//    @PostMapping("/NewStaff")
//    public NhanVien createStaff(@Valid @RequestBody NhanVien nhanVien) {
//        return staffRepository.save(nhanVien);
//    }
//


//    @DeleteMapping("/{staffId}")
//    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String maNhanVien) {
//        boolean deleted = staffService.deleteStaff(maNhanVien);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
 ///////////////////////////////////////////////////////

    // Tìm kiếm sản phẩm trên giao diện productList
    @GetMapping("/searchName")
    public String SearchName(UriComponentsBuilder uriBuilder,
                              @RequestParam("username") String username, @Param("username") String username1,
                              @RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request){
        username = request.getParameter("username");
        Pageable pageable = PageRequest.of(0, 5);
        List<Staff> listStaff = staffService.searchUsername(username);
        Page<Staff> pageStaff = new PageImpl<>(listStaff, pageable, listStaff.size());
        model.addAttribute("staffPage", pageStaff);
        return "staff/staffList";
    }

    // Xoá sản phẩm trên giao diện productList
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") String id, UriComponentsBuilder uriBuilder,
                             @RequestParam(defaultValue = "0") int page, Model model) {
        boolean isDel = staffService.deleteStaff(id);
        if (isDel == true) {
            model.addAttribute("isOk", "Xóa nhân viên với mã " + id + " thành công!");
        }
        return getStaffs(page, model, uriBuilder);
    }

    @ModelAttribute("STAFF")
    public Staff initStaff() {
        return new Staff();
    }

    // hiện giao diện New staff
    @RequestMapping(value = "/newStaff", method = RequestMethod.GET)
    public String newPage(Model model) {
        return "staff/staffNew";
    }

    // Hiển thị staffList sau khi tạo sản phẩm mới và hiện thông báo
    @PostMapping("/saveStaff")
    public String saveStaff(Model model, Staff staff, UriComponentsBuilder uriBuilder,
                            @RequestParam(defaultValue = "0") int page) {

        if (staffRepository.findById(staff.getId())  != null){
            model.addAttribute("isUpdate", "Cập nhật nhân viên có mã " + staff.getId() + " thành công!!");
            staffRepository.save(staff);
            return getStaffs(page,model,uriBuilder);

        }else{
            model.addAttribute("mess", "Save staff Successfully!!");
            staffRepository.save(staff);
            return newPage(model);
        }
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable("id") String id,Model  model){
        Staff  nv = staffRepository.findById(id).get();
        if (nv != null){
            model.addAttribute("isUpdate", "no update id");
            model.addAttribute("STAFF",staffRepository.findById(id));
        }
        return "staff/editStaff";
    }
}