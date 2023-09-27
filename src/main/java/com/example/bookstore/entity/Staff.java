package com.example.bookstore.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
@Builder
@Component
public class Staff {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "^KH-\\d{4}$")
    @Column(name = "id", nullable = false)
    @NotBlank(message = "Không được để trống")
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "date", nullable = false)
    private java.sql.Date date;
    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "^(090|091|849[01])[0-9]{7}$", message = "Số điện thoại không hợp lệ")
    private String phone;
    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email không hợp lệ")
    private String email;
    @Column(name = "address", nullable = false)
    private String address;

    // tạo bảng users_roles có 2 thuộc tính
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    public Set<String> getRoleNames() {
        Set<String> roleNames = new HashSet<>();
        for (Role role : roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }
}
