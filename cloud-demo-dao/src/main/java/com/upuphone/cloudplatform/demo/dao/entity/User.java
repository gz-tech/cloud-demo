package com.upuphone.cloudplatform.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.upuphone.cloudplatform.mybatis.basic.BasicEntity;
import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 14:39
 */
@Data
@TableName("t_user")
public class User extends BasicEntity<User> {

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "nick_name")
    private String nickName;

    private Integer age;
}
