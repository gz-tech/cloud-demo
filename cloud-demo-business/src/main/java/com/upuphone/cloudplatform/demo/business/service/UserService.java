package com.upuphone.cloudplatform.demo.business.service;

import com.upuphone.cloudplatform.demo.api.dto.request.UserRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.UserResponse;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 11:18
 */
public interface UserService {

    String getUserName(Long userId);

    UserResponse findUser(UserRequest userRequest);
}
