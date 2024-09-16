package com.example.EventHub.Controllers;

import com.example.EventHub.Models.AuthRequest;
import com.example.EventHub.Services.ServiceInterfaces.IUserService;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final IUserService userService;

    @PostMapping({"/authenticate"})
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        return this.userService.createAuthenticationToken(authRequest);
    }

    @GetMapping({"/admin"})
    public String adminAccess() {
        return "Admin access granted!";
    }

    @GetMapping({"/user"})
    public String userAccess() {
        return "User access granted!";
    }

    @Generated
    public AuthController(final IUserService userService) {
        this.userService = userService;
    }
}
