package com.example.facebookweek7task.dto;

import com.example.facebookweek7task.entity.User;
import lombok.Data;

@Data
public class ResponseDTO {
    private String message;
    private User data;
    private boolean status;


}
