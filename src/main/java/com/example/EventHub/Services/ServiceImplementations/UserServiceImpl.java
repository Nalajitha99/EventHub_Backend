package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Repositories.UserRepository;
import com.example.EventHub.Services.ServiceInterfaces.IUserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserDto saveUser(UserDto userDto){
        userRepository.save(modelMapper.map(userDto, User.class));
        return userDto;
    }
}
