package com.upuphone.cloudplatform.demo.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import com.upuphone.cloudplatform.demo.dao.entity.User;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 13:46
 */
public interface DemoService extends IService<User> {

    String demoExecute(String args);

    Long insert(DemoBo demoBo);

    PageDTO<DemoBo> selectList(int pageNum, int pageSize);

    boolean delete(Long id);

    String redis(String args);

    String producerRocketmq(String topic);

    String consumerRocketmq();
}
