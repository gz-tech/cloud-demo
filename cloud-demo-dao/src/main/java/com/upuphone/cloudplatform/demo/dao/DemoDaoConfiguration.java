package com.upuphone.cloudplatform.demo.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 14:41
 */
@Configuration
@MapperScan("com.upuphone.cloudplatform.demo.dao.mapper")
public class DemoDaoConfiguration {
}
