package com.upuphone.cloudplatform.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upuphone.cloudplatform.demo.dao.po.UserQueryPO;
import com.upuphone.cloudplatform.demo.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 14:46
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findUser(UserQueryPO userQueryPO);
}
