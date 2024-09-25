package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.AuthRequest;
import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Repositories.UserRepository;
import com.example.EventHub.Security.jwtUtil;
import com.example.EventHub.Services.ServiceInterfaces.IUserService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private jwtUtil jwtutil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
    }

    public UserDto saveUser(UserDto userDto) {
        this.userRepository.save((User)this.modelMapper.map(userDto, User.class));
        return userDto;
    }

    public UserDto getUserById(String userId) {
        User user = this.userRepository.getUserByUserId(userId);
        return (UserDto)this.modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        return null;
    }

    public boolean UserExist(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    public ResponseEntity<Object> createAuthenticationToken(AuthRequest authRequest) throws Exception {
        try {
            if (this.UserExist(authRequest.getUsername())) {
                User user = this.userRepository.findByUsername2(authRequest.getUsername());
                if (user != null && this.passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), (Object)null, new ArrayList());
                    String token = this.jwtutil.generateToken(authentication);
                    authRequest.setToken(token);
                    authRequest.setMessage("Login Successful");
                } else {
                    authRequest.setMessage("Invalid Password");
                }
            } else {
                authRequest.setMessage("User does not exist");
            }

            return ResponseEntity.ok(authRequest);
        } catch (Exception var5) {
            Exception e = var5;
            throw new Exception("Error not log in", e);
        }
    }
}
