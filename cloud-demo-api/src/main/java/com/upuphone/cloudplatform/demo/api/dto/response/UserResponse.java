package com.upuphone.cloudplatform.demo.api.dto.response;

import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 11:20
 */
@Data
public class UserResponse {

    private Long userId;
    private String userName;
    private String nickName;
}
