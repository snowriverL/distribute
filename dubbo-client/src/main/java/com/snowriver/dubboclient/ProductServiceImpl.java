package com.snowriver.dubboclient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.snowriver.dubboserver.service.LoginService;
import org.springframework.stereotype.Service;

@Service("dubbo-server")
public class ProductServiceImpl implements ProductService {


    /**
     * 使用dubbo的注解 com.alibaba.dubbo.config.annotation.Reference。进行远程调用service
     */
    @Reference
    private LoginService loginService;

    @Override
    public String getCost(String a, String b) {
        return loginService.login(a, b);
    }

}