package com.fpfos.controller;

import com.fpfos.bean.core.LoginInputBean;
import com.fpfos.bean.core.LoginOutputBean;
import com.fpfos.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginOutputBean login(LoginInputBean input) {

        return loginService.service(input);
    }
}
