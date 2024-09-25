package com.example.EventHub.Models.Domains;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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


}

