package com.upuphone.cloudplatform.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.upuphone.cloudplatform.mybatis.basic.BasicEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 20:16
 */
@Data
@TableName("t_demo")
public class Demo extends BasicEntity<Demo> {

    @TableField(value = "url")
    private String url;


}
