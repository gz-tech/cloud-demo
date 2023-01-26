package com.upuphone.cloudplatform.demo.dao.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Classname AA
 * @Description TODO
 * @Date 2022/5/3 12:25 下午
 * @Created by gz-d
 */
@Component
@Data
public class AA {

    private int errCode;
    private String errmsg;
    @Autowired
    private BB bb;

    public AA() {
        System.out.println("BBBBBBB" + bb);
    }


    @PostConstruct
    private void init() {
        System.out.println("affffbc");
    }

}
