package com.example.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer isDelete;
    private Integer isLogin;

}
