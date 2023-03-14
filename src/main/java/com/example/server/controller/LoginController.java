package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.server.common.R;
import com.example.server.entity.Login;
import com.example.server.entity.User;
import com.example.server.entity.dto.LoginAndUser;
import com.example.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.server.service.LoginService;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginservice;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;



    //登录
    public R login(String username, String password){
        if(username.isEmpty()||password.isEmpty()){
            return R.error("用户名或密码为空!");
        }
        LambdaQueryWrapper<Login> loginLambdaQueryWrapper = new LambdaQueryWrapper<>();
        loginLambdaQueryWrapper.eq(Login::getUsername,username).eq(Login::getPassword,password);
        Login login = loginservice.getOne(loginLambdaQueryWrapper);
        if(login.getIsLogin()==1){
            return R.error("当前账号已登录!");
        }
        LambdaUpdateWrapper<Login> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Login::getId,login.getId()).set(Login::getIsLogin,0);
        loginservice.update(lambdaUpdateWrapper);
        return R.success(login.getId());
    }

    public R<String> updateLoginAndUser(LoginAndUser data){


        log.info(String.valueOf(data));

        User user = data.getUser();
        user.setIsLogin(0);
        user.setId(userService.count()+1);

        Login login = data.getLogin();
        login.setIsLogin(0);
        login.setIsDelete(0);
        login.setId(loginservice.count()+1);

        loginservice.save(login);
        userController.updateUser(user);

        return R.success("注册成功");
    }




}
