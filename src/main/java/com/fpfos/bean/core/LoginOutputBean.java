package com.fpfos.bean.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOutputBean extends AbstractOutputBean {

    private String userID;
    private String username;
    private int age;
}
