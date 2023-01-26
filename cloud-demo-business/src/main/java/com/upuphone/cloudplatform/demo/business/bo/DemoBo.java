package com.upuphone.cloudplatform.demo.business.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/28 20:49
 */
@Data
public class DemoBo {

    private Long id;

    private String url;

    private boolean isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
