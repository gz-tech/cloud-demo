package com.upuphone.cloudplatform.demo.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.upuphone.cloudplatform.demo.api.dto.request.UserRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.UserResponse;
import com.upuphone.cloudplatform.demo.business.service.UserService;
import com.upuphone.cloudplatform.demo.dao.entity.User;
import com.upuphone.cloudplatform.demo.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 13:46
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserName(Long userId) {
        User user = userMapper.selectById(userId);
        return user.getUserName();
    }

    @Override
    public UserResponse findUser(UserRequest userRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("nick_name", userRequest.getNickName());
        User user = userMapper.selectOne(queryWrapper);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setNickName(user.getNickName());
        return userResponse;
    }
}
