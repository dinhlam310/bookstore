package com.example.bookstore.config;

import com.example.bookstore.entity.Staff;
import com.example.bookstore.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceConfig implements UserDetailsService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff user = staffRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find username");
        }
        return new MyUserDetail(user);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
        Staff user = staffRepository.findById(String.valueOf(id)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new MyUserDetail(user);
    }

}
