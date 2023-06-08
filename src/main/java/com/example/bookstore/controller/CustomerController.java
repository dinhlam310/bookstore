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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

//    @GetMapping("/page/{page}")
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public String getCustomers(@PathVariable int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("MaKhachHang").ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, 5, sort);
        Page<KhachHang> customerPage = customerRepository.findAll(pageRequest);

        model.addAttribute("customerPage", customerPage);

        String previousUrl = uriBuilder.path("/page/{page}").buildAndExpand(page - 1).toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/page/{page}").buildAndExpand(page + 1).toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "customerList";
    }

    @GetMapping("/{MaKhachHang}")
    public ResponseEntity<KhachHang> getCustomerByMaKhachHang(@PathVariable(value = "MaKhachHang") String MaKhachHang)
            throws ResourceNotFoundException {
        KhachHang khachHang = customerRepository.findByMaKhachHang(MaKhachHang)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với mã khách hàng: " + MaKhachHang));
        return ResponseEntity.ok().body(khachHang);
    }

    @PostMapping("/newCustomer")
    public KhachHang createCustomer(@Valid @RequestBody KhachHang khachHang) {
        return customerRepository.save(khachHang);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("customerId") String MaKhachHang, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(MaKhachHang, customerDTO);
        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String MaKhachHang) {
        boolean deleted = customerService.deleteCustomer(MaKhachHang);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}