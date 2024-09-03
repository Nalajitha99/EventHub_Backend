package com.example.EventHub.Controllers;

import com.example.EventHub.Models.Dtos.UserDto;
import com.example.EventHub.Services.ServiceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(userService.saveUser(userDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getUserById/{userId}")
    public UserDto getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }
}
