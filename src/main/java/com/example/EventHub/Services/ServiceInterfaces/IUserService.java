package com.example.EventHub.Services.ServiceInterfaces;

import com.example.EventHub.Models.Dtos.UserDto;

public interface IUserService {
    UserDto saveUser(UserDto userDto);
}
