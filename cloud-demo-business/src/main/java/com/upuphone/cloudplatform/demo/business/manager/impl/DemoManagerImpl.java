package com.upuphone.cloudplatform.demo.business.manager.impl;

import com.upuphone.cloudplatform.demo.api.dto.request.DemoRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.DemoResponse;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import com.upuphone.cloudplatform.demo.business.manager.DemoManager;
import com.upuphone.cloudplatform.demo.business.service.DemoService;
import com.upuphone.cloudplatform.demo.business.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/28 17:50
 */
@Slf4j
@Service
public class DemoManagerImpl implements DemoManager {

    @Resource
    private UserService userService;

    @Resource
    private DemoService demoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DemoResponse doSomething(DemoRequest demoRequest) {
        Long userId = demoRequest.getUserId();
        String userName = userService.getUserName(userId);
        DemoBo demoBo = new DemoBo();
        demoBo.setUrl("url:" + userName);
        Long id = demoService.insert(demoBo);
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setId(id);
        demoResponse.setUrl(demoBo.getUrl());
        return demoResponse;
    }
}
