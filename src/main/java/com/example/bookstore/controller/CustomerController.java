package com.example.bookstore.controller;

import com.example.bookstore.DTO.CustomerDTO;
import com.example.bookstore.entity.KhachHang;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.service.CustomerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/page/{page}")
    public String getCustomers(@PathVariable int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("maKhachHang").ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, 5, sort);
        Page<KhachHang> customerPage = customerRepository.findAll(pageRequest);

        model.addAttribute("customerPage", customerPage);

        String previousUrl = uriBuilder.path("/page/{page}").buildAndExpand(page - 1).toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/page/{page}").buildAndExpand(page + 1).toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "CustomerList";
    }

    @GetMapping("/{maKhachHang}")
    public ResponseEntity<KhachHang> getCustomerByMaKhachHang(@PathVariable(value = "maKhachHang") String maKhachHang)
            throws ResourceNotFoundException {
        KhachHang khachHang = customerRepository.findByMaKhachHang(maKhachHang)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với mã khách hàng: " + maKhachHang));
        return ResponseEntity.ok().body(khachHang);
    }

    @PostMapping("/newCustomer")
    public KhachHang createCustomer(@Valid @RequestBody KhachHang khachHang) {
        return customerRepository.save(khachHang);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("customerId") String maKhachHang, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(maKhachHang, customerDTO);
        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String maKhachHang) {
        boolean deleted = customerService.deleteCustomer(maKhachHang);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}