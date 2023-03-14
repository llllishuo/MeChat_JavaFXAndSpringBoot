package com.example.UI.service;


import com.example.server.entity.User;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Data
@Scope("prototype")
public class SendUser {
    int userId;
}
