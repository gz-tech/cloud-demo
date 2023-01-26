package com.upuphone.cloudplatform.demo.api;

import com.upuphone.cloudplatform.demo.api.dto.request.UserRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 11:18
 */
@FeignClient(name = "cloud-demo-service", contextId = "cloud-demo-service-context")
@RequestMapping("/user")
public interface UserClientApi {

    @GetMapping("/getUserName")
    String getUserName(@RequestParam("userId") Long userId);

    @PostMapping(value = "/findUser")
    UserResponse findUser(@RequestBody UserRequest userRequest);
}
