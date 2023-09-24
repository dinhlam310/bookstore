package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.service.CustomerService;
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
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "customerList/page", method = RequestMethod.GET)
    public String getCustomers(@RequestParam(defaultValue = "0") int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("id").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);
        Page<Customer> customerPage = customerRepository.findAll(pageRequest);

        model.addAttribute("customerPage", customerPage);

        String previousUrl = uriBuilder.path("/customerList/page").queryParam("page", page - 1).build().toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/customerList/page").queryParam("page", page + 1).build().toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "customer/customerList";
    }

    @GetMapping("/findCustomer")
    public String FindCustomer(UriComponentsBuilder uriBuilder,
                              @RequestParam("name") String name, @Param("name") String name1,
                              @RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request){

        name = request.getParameter("name");
        Pageable pageable = PageRequest.of(0, 5);

        List<Customer> listCustomer = customerService.searchName(name);
        Page<Customer> pageCustomer = new PageImpl<>(listCustomer, pageable, listCustomer.size());
        model.addAttribute("customerPage", pageCustomer);

        return "customer/customerList";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") String id, UriComponentsBuilder uriBuilder,
                             @RequestParam(defaultValue = "0") int page, Model model) {
        boolean isDel = customerService.deleteCustomer(id);
        if (isDel == true) {
            model.addAttribute("isOk", "Xóa khách hàng với mã " + id + " thành công!");
        }
        return getCustomers(page, model, uriBuilder);
    }

    @ModelAttribute("CUSTOMER")
    public Customer initCustomer() {
        return new Customer();
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        return "customer/customerNew";
    }

    // Hiển thị customerList sau khi tạo khách hàng mới và hiện thông báo
    @PostMapping("/saveCustomer")
    public String saveCustomer(Model model, Customer customer, UriComponentsBuilder uriBuilder,
                               @RequestParam(defaultValue = "0") int page) {

        if (customerRepository.findById(customer.getId())  != null){
            model.addAttribute("isUpdate", "Cập nhật khách hàng có mã " + customer.getId() + " thành công!!");
            customerRepository.save(customer);
            return getCustomers(page,model,uriBuilder);

        }else{
            model.addAttribute("mess", "Save customer Successfully!!");
            customerRepository.save(customer);
            return newCustomer(model);
        }
    }

    // chuyển sang giao diện productNew và chỉnh sửa sản phẩm khi bấm nút sửa bên productList
    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable("id") String id,Model  model){
        Customer  kh = customerRepository.findById(id).get();
        if (kh != null){
            model.addAttribute("isUpdate", "no update id");
            model.addAttribute("CUSTOMER",customerRepository.findById(id));
        }
        return "customer/editCustomer";
    }

//@RequestMapping(value = "/{MaKhachHang}", method = RequestMethod.GET)
//    public ResponseEntity<KhachHang> getCustomerByMaKhachHang(@PathVariable(value = "MaKhachHang") String MaKhachHang)
//            throws ResourceNotFoundException {
//        KhachHang khachHang = customerRepository.findByMaKhachHang(MaKhachHang)
//                .orElseThrow(() -> new ResourceNotFoundException("Không thể tìm thấy khách hàng với mã khách hàng: " + MaKhachHang));
//        return ResponseEntity.ok().body(khachHang);
//    }
//
//    @GetMapping("/allCustomers")
//    public ResponseEntity<List<KhachHang>> getAllCustomers(Pageable pageable) {
//        Page<KhachHang> page = customerRepository.findAll(pageable);
//        List<KhachHang> khachHangList = page.getContent();
//        return new ResponseEntity<>(khachHangList, HttpStatus.OK);
//    }


    //@PostMapping(value = "/newCustomer", consumes = "application/json", produces = "application/json")
//    @RequestMapping(value = "/newCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> createCustomer(@Valid @RequestBody KhachHang khachHang, BindingResult result) {
//        if (result.hasErrors()) {
//            return new ResponseEntity<>(getErrors(result), HttpStatus.BAD_REQUEST);
//        } else {
//            try {
//                KhachHang khachHangMoi = new KhachHang();
//                khachHangMoi.setMaKhachHang(khachHang.getMaKhachHang());
//                khachHangMoi.setTenKhachHang(khachHang.getTenKhachHang());
//                khachHangMoi.setEmail(khachHang.getEmail());
//                khachHangMoi.setSoDienThoai(khachHang.getSoDienThoai());
//                khachHangMoi.setNgaySinh(khachHang.getNgaySinh());
//                khachHangMoi.setLoaiKhachHang(khachHang.getLoaiKhachHang());
//                khachHangMoi.setTongChiTieu(khachHang.getTongChiTieu());
//                customerRepository.save1(khachHangMoi.getMaKhachHang(), khachHangMoi.getTenKhachHang(), khachHangMoi.getEmail(), khachHangMoi.getSoDienThoai(), khachHangMoi.getNgaySinh(), khachHangMoi.getLoaiKhachHang(), khachHangMoi.getTongChiTieu());
//                return new ResponseEntity<>("Tạo khách hàng mới thành công", HttpStatus.CREATED);
//            } catch (Exception e) {
//                String errorMessage = "Lỗi: " + e.getMessage();
//                return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
//            }
//        }
//    }

//    private List<String> getErrors(BindingResult result) {
//        List<String> errors = new ArrayList<>();
//        for (FieldError error : result.getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        return errors;
//    }


//    @RequestMapping(value = "/editCustomer/{customerId}", method = RequestMethod.GET)
//    public String checkProduct(Model model, @PathVariable("customerId") String maKhachHang) {
//        KhachHang khachHang = customerService.getCustomerByMaKhachHang(maKhachHang);
//        if (khachHang == null) {
//            model.addAttribute("error", "Không tìm thấy sản phẩm với mã " + maKhachHang);
//        } else {
//            model.addAttribute("customer", new KhachHang(khachHang.getMaKhachHang(), khachHang.getTenKhachHang(), khachHang.getEmail(), khachHang.getSoDienThoai(), khachHang.getNgaySinh(), khachHang.getLoaiKhachHang(), khachHang.getTongChiTieu()));
//        }
//        return "editCustomer";
//    }
//
//    @RequestMapping(value = "/editCustomer/{customerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") String MaKhachHang, @RequestBody KhachHang khachHang) {
//        KhachHang updateCustomer = customerRepository.save(khachHang);
//        if (updateCustomer == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(updateCustomer);
//    }
//
//    @DeleteMapping("/editCustomer/{customerId}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String MaKhachHang) {
//        boolean deleted = customerService.deleteCustomer(MaKhachHang);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}