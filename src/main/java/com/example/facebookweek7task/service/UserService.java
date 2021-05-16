package com.example.facebookweek7task.service;

import com.example.facebookweek7task.dto.ResponseDTO;
import com.example.facebookweek7task.entity.User;


public interface UserService {
    ResponseDTO addUser(User user);

    ResponseDTO logInUser(User user);

}
