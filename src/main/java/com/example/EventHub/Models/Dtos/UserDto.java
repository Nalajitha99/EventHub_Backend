package com.example.EventHub.Models.Dtos;

import com.example.EventHub.Models.Domains.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private long customerId;
    private String firstName;
    private String lastName;
    private Role role;
    private String nic;
    private String email;
    private String username;
    private String password;
    private String contactNo;
    private String address;
    private String gender;
}
