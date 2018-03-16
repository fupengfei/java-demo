package com.fpfos.service;

import com.fpfos.bean.core.AbstractOutputBean;
import com.fpfos.bean.core.LoginInputBean;
import com.fpfos.bean.core.LoginOutputBean;
import com.fpfos.service.core.ApiService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService implements ApiService<LoginInputBean, LoginOutputBean> {

    @Override
    public LoginOutputBean service(LoginInputBean input) {

        /**
         * 数据验证
         */
        if (null == input.getLoginname() || "".equals(input.getLoginname().trim())) {
            return AbstractOutputBean.error(LoginOutputBean.class, "用户登录名不能为空");
        }
        if (null == input.getLoginpasswd() || "".equals(input.getLoginpasswd().trim())) {
            return AbstractOutputBean.error(LoginOutputBean.class, "用户登录密码不能为空");
        }

        if (!"admin".equals(input.getLoginname()) || "123456".equals(input.getLoginpasswd())) {
            return AbstractOutputBean.error(LoginOutputBean.class, "用户名或密码错误");
        }

        /**
         * 封装返回参数
         */
        LoginOutputBean output = new LoginOutputBean();
        output.setUserID(UUID.randomUUID().toString().replace("-", ""));
        output.setUsername("小恩");
        output.setAge(24);

        return output;
    }
}
