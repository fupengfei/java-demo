package com.fpfos.service.core;

import com.fpfos.bean.core.AbstractInputBean;
import com.fpfos.bean.core.AbstractOutputBean;

public interface ApiService<I extends AbstractInputBean, O extends AbstractOutputBean> {

    O service(I input);
}
