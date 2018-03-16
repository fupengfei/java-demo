package com.fpfos.bean.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AbstractOutputBean implements Serializable {

    private Integer code;

    private String errmsg;

    public static <T extends AbstractOutputBean> T success(Class<T> t)  {
        T output = null;
        try {
            output = t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        output.setCode(0);
        return output;
    }

    public static <T extends AbstractOutputBean> T error(Class<T> t, String errmsg) {

        return error(t, 1, errmsg);
    }

    public static <T extends AbstractOutputBean> T error(Class<T> t, Integer code, String errmsg)  {
        T output = null;
        try {
            output = t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        output.setCode(code);
        output.setErrmsg(errmsg);
        return output;
    }

}
