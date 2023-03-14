package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.User;
import com.example.server.mapper.UserMapper;
import com.example.server.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getUserData(int id) {
        User user = getById(id);


        return user;
    }
}
