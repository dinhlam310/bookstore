package com.example.bookstore.controller;

import com.example.bookstore.config.MyUserDetail;
import com.example.bookstore.entity.LoginResponse;
import com.example.bookstore.entity.Staff;
import com.example.bookstore.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Transactional
//@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse authenticateUser(@Valid @RequestBody MyUserDetail myUserDetail) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        myUserDetail.getUsername(),
                        myUserDetail.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((MyUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

//    @PostConstruct
//    public void init(){
//        Staff employee = Staff.builder()
//                .id("NV-1001").password(passwordEncoder.encode("123")).username("tuan").build();
//        staffRepository.save(employee);
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
         return "loginPage";
    }

    @RequestMapping(value = "/LoginSuccess", method = RequestMethod.GET)
    public String loginSuccessPage(Model model) {
        return "loginSuccessPage";
    }

    @RequestMapping(value = "/login?error=true", method = RequestMethod.GET)
    public String wrongInfoLogin(Model model) {
        model.addAttribute("error","Sai tài khoản hoặc mật khẩu , hãy nhập lại");
        return "loginPage";
    }
//
//    @RequestMapping(value = { "/", "/welcome","../" }, method = RequestMethod.GET)
//    public String welcomePage(Model model) {
//        model.addAttribute("title", "Xin chào người dùng , hiện tại bạn chưa thể sử dụng bất kì chức năng nào");
//        model.addAttribute("message", "Xin hãy đăng nhập để có thể sử dụng toàn bộ chức năng");
//        return "index";
//    }

    //Đây là trang Admin
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(Model model, Principal principal) {
//
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//
//        return "adminPage";
//    }

//    @RequestMapping(value = "/403", method = RequestMethod.GET)
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//            String userInfo = WebUtils.toString(loginedUser);
//
//            model.addAttribute("userInfo", userInfo);
//
//            String message = "Hi " + principal.getName() //
//                    + "<br> You do not have permission to access this page!";
//            model.addAttribute("message", message);
//
//        }
//
//        return "403Page";
//    }

    // khi người dùng đăng nhập thành công
//    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
//    public String userInfo(Model model, Principal principal) {
//
//        // Sau khi user login thanh cong se co principal
//        String userName = principal.getName();
//
//        System.out.println("Tên người dùng : " + userName);
//
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//
//        return "userInfoPage";
//    }

    // khi người dùng logout khỏi hệ thống
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
//
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        // Lấy mã lỗi HTTP từ request
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode != null) {
//            // Trả về trang lỗi tương ứng với mã lỗi HTTP
//            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error-404";
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error-500";
//            }
//        }
//        // Nếu không phải mã lỗi HTTP đã biết, trả về trang lỗi mặc định
//        return "error";
//    }



}







