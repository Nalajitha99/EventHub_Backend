package com.example.EventHub.Models.Domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    private long customerId;
    private String firstName;
    private String lastName;
    private String role;
    private long nic;
    private String email;
    private String username;
    private String password;
    private String contactNo;
    private String address;
    private String gender;
}
