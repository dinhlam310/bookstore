package com.example.bookstore.controller;

import com.example.bookstore.DTO.CustomerDTO;
import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.service.CustomerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "customerList/page", method = RequestMethod.GET)
    public String getCustomers(@RequestParam(defaultValue = "0") int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("MaKhachHang").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);
        Page<KhachHang> customerPage = customerRepository.findAll(pageRequest);

        model.addAttribute("customerPage", customerPage);

        String previousUrl = uriBuilder.path("/customerList/page").queryParam("page", page - 1).build().toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/customerList/page").queryParam("page", page + 1).build().toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "customerList";

    }

@RequestMapping(value = "/{MaKhachHang}", method = RequestMethod.GET)
    public ResponseEntity<KhachHang> getCustomerByMaKhachHang(@PathVariable(value = "MaKhachHang") String MaKhachHang)
            throws ResourceNotFoundException {
        KhachHang khachHang = customerRepository.findByMaKhachHang(MaKhachHang)
                .orElseThrow(() -> new ResourceNotFoundException("Không thể tìm thấy khách hàng với mã khách hàng: " + MaKhachHang));
        return ResponseEntity.ok().body(khachHang);
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<KhachHang>> getAllCustomers(Pageable pageable) {
        Page<KhachHang> page = customerRepository.findAll(pageable);
        List<KhachHang> khachHangList = page.getContent();
        return new ResponseEntity<>(khachHangList, HttpStatus.OK);
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public String newPage() {
        return "customerNew";
    }

    //@PostMapping(value = "/newCustomer", consumes = "application/json", produces = "application/json")
    @RequestMapping(value= "/newCustomer",method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody KhachHang khachHang, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getErrors(result), HttpStatus.BAD_REQUEST);
        } else{
            KhachHang khachHangmoi = new KhachHang(khachHang.getMaKhachHang(),khachHang.getTenKhachHang(), khachHang.getEmail(),khachHang.getSoDienThoai(),khachHang.getNgaySinh(),khachHang.getLoaiKhachHang(),khachHang.getTongChiTieu());
            customerRepository.save(khachHangmoi);
            return new ResponseEntity<>("Tạo khách hàng thành công", HttpStatus.CREATED);
        }
    }

    private List<String> getErrors(BindingResult result) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return errors;
    }


    @RequestMapping(value = "/updateCustomer", method = RequestMethod.GET)
    public String updateCustomer() {
        return "customerUpdate";
    }
    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("customerId") String maKhachHang, @RequestBody CustomerDTO customerDTO) {
        Optional<CustomerDTO> updatedCustomerOptional = customerService.updateCustomer(maKhachHang, customerDTO);
        if (updatedCustomerOptional.isPresent()) {
            return ResponseEntity.ok(updatedCustomerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String MaKhachHang) {
        boolean deleted = customerService.deleteCustomer(MaKhachHang);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}