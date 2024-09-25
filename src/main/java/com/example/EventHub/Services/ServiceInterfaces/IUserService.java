package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.AuthRequest;
import com.example.EventHub.Models.Dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    UserDto saveUser(UserDto userDto);
    UserDto getUserById(String userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUserById(long userId);

    ResponseEntity<Object> createAuthenticationToken(AuthRequest authRequest) throws Exception;
}
