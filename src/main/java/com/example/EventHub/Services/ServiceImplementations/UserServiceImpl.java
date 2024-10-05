package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.AuthRequest;
import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.EmailVerifyDto;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Models.Dtos.UserResponseDto;
import com.example.EventHub.Repositories.UserRepository;
import com.example.EventHub.Security.jwtUtil;
import com.example.EventHub.Services.ServiceInterfaces.IUserService;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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

    private final JavaMailSender javaMailSender;


    public UserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<Object> saveUser(UserDto userDto) {

        if(this.UserExist(userDto.getUsername())) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setMessage("User already exists");
            return ResponseEntity.badRequest().body(userResponseDto);
        }else{
            User user = (User)this.modelMapper.map(userDto, User.class);
            Random random = new Random();
            int otp=100000+ random.nextInt(900000);
            user.setOtp(otp);
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setMessage("User created successfully");
            UserResponseDto emailResponse=sendEmail(userDto.getEmail(),otp);
            userResponseDto.setEmailResponse(emailResponse.getEmailResponse());
            this.userRepository.save(user);
            return ResponseEntity.ok(userResponseDto);

        }
    }

    public UserResponseDto sendEmail(String email,int otp){
        UserResponseDto userDTO = new UserResponseDto();
        try{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setSubject("Hello User");
            message.setTo(email);
            message.setText("Please use this OTP to verify your email:"+" "+otp);
            message.setFrom("hubevenlk@gmail.com");


            javaMailSender.send(message);
            userDTO.setEmailResponse("Verification OTP send Successfully to" +" "+ email);

        }
        catch (Exception e){
            throw new IllegalArgumentException("Email not sent"+e);
        }
        return userDTO;
    }

    public EmailVerifyDto verifyEmail(EmailVerifyDto emailVerifyDTO) {
        User user = userRepository.findByEmail(emailVerifyDTO.getEmail());
        if(user != null){
            if(user.isVerified()){
                throw new IllegalArgumentException("Email already verified");
            }
            else if(user.getOtp()==emailVerifyDTO.getOtp()){
                userRepository.verifyByEmail(emailVerifyDTO.getEmail());
                emailVerifyDTO.setMessage("Email verified successfully");
                emailVerifyDTO.setOtp(0);
            }
            else{
                throw new IllegalArgumentException("Invalid OTP");
            }
        }
        else{
            throw new IllegalArgumentException("Email does not exist");
        }
        return emailVerifyDTO;
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

    @Override
    public void deleteUserById(long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
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
                    authRequest.setRole(String.valueOf(user.getRole()));
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

    @Scheduled(fixedRate = 60000)
    public void deleteUnverifiedUsers() {

        userRepository.deleteByCreatedDateBeforeAndVerifiedFalse(LocalDateTime.now().minusMinutes(1));
    }
}
