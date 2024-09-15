package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Repositories.UserRepository;
import com.example.EventHub.Services.ServiceInterfaces.IUserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto){
        userRepository.save(modelMapper.map(userDto, User.class));
        return userDto;
    }

    @Override
    public UserDto getUserById(String userId){
        User user = userRepository.getUserByUserId(userId);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User u = user.get();
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()))  // Assign roles
        );
    }
}
