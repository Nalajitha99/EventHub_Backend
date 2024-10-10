package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.AuthRequest;
import com.example.EventHub.Models.Dtos.EmailVerifyDto;
import com.example.EventHub.Models.Dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    ResponseEntity<Object> saveUser(UserDto userDto);
    UserDto getUserById(String userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUserById(long userId);
    EmailVerifyDto verifyEmail(EmailVerifyDto emailVerifyDTO);
    UserDto getUserByUsername(String username);

    ResponseEntity<Object> createAuthenticationToken(AuthRequest authRequest) throws Exception;
}
