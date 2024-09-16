package com.example.EventHub.Services.ServiceImplementations;

import com.example.EventHub.Models.Domains.User;
import com.example.EventHub.Repositories.UserRepository;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            User u = (User)user.get();
            return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_" + u.getRole().name())));
        }
    }

}
