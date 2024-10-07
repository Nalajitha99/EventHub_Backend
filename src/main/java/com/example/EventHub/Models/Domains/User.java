package com.example.EventHub.Models.Domains;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Setter(AccessLevel.NONE)
    private String password;

    private String contactNo;
    private String address;
    private String gender;

    private boolean verified = false;
    private int otp;
    private LocalDateTime createdDate = LocalDateTime.now();

    public void setPassword(String rawPassword) {
        this.password = new BCryptPasswordEncoder().encode(rawPassword);
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

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getOtp() {
        return otp;
    }


    public boolean isVerified() {
        return false;
    }
}

