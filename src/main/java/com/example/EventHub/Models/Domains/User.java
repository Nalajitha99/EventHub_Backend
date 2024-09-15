package com.example.EventHub.Models.Domains;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String nic;
    private String email;
    private String username;

    @Setter(AccessLevel.NONE)  // Prevent direct modification of password
    private String password;

    private String contactNo;
    private String address;
    private String gender;

    public void setPassword(String rawPassword) {
        this.password = new BCryptPasswordEncoder().encode(rawPassword);  // Encrypt password
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}

