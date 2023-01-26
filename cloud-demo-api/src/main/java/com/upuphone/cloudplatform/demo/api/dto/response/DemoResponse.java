package com.upuphone.cloudplatform.demo.api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/28 17:58
 */
@Data
public class DemoResponse {

    private Long id;

    private String url;

    private boolean isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
