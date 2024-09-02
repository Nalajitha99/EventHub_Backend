package com.example.EventHub.Models.Dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
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
