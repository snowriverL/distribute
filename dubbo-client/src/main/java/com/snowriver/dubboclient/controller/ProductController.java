package com.snowriver.dubboclient.controller;

import com.snowriver.dubboclient.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品controller
 */
@RestController
@RequestMapping("dubbo")
public class ProductController {


    @Autowired
    private ProductService productService;

    /**
     * 添加完 返回总共消费
     * @param a
     * @return
     */
    @GetMapping("/dubbo-client")
    public String getCost(int a){
        return "该产品总共消费 ："+productService.getCost("guofuhao", "snowriver");
}
}