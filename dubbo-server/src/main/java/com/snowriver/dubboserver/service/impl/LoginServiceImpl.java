package com.snowriver.dubboserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.snowriver.dubboserver.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public String login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return "SUCCESS";
        }
        return "FAILED";
    }
}