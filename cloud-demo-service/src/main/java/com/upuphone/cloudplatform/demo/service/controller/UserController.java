package com.upuphone.cloudplatform.demo.service.controller;

import com.upuphone.cloudplatform.demo.api.UserClientApi;
import com.upuphone.cloudplatform.demo.api.dto.request.UserRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.UserResponse;
import com.upuphone.cloudplatform.demo.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/21 10:49
 */
@RestController
@Api(value = "用户demo", tags = "用户demo")
public class UserController implements UserClientApi {

    @Resource
    private UserService userService;

    @Value("${server.port}")
    private Integer serverPort;

    @Override
    @ApiOperation(value = "获取用户名")
    public String getUserName(@RequestParam("userId") Long userId) {
        return userService.getUserName(userId) + "_" + serverPort;
    }

    @Override
    @ApiOperation(value = "查询用户")
    public UserResponse findUser(@RequestBody UserRequest userRequest) {
        return userService.findUser(userRequest);
    }

}
