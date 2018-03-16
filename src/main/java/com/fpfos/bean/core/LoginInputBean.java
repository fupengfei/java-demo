package com.fpfos.bean.core;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginInputBean extends AbstractInputBean {

    private String loginname;
    private String loginpasswd;
}
