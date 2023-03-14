package com.example.server.entity.dto;

import com.example.server.entity.Login;
import com.example.server.entity.User;
import lombok.Data;

@Data
public class LoginAndUser {

    private Login login;

    private User user;
}
