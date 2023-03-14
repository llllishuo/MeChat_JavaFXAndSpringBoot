package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Blog;
import com.example.server.entity.User;

public interface UserService extends IService<User> {
    User getUserData(int id);

}
