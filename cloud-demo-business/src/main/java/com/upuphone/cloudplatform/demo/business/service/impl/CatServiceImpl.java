package com.upuphone.cloudplatform.demo.business.service.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.upuphone.cloudplatform.cat.annotation.CatTransaction;
import com.upuphone.cloudplatform.demo.api.UserClientApi;
import com.upuphone.cloudplatform.demo.api.dto.request.UserRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.UserResponse;
import com.upuphone.cloudplatform.demo.business.service.CatService;
import com.upuphone.cloudplatform.demo.business.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/2/8 15:25
 */
@Slf4j
@Service
public class CatServiceImpl implements CatService {

    @Resource
    private UserClientApi userClientApi;

    @Resource
    private UserService userService;


    @Override
    public String getUserName(Long id) {
        Cat.logEvent("Demo.Event", "event");
        Cat.logEvent("Demo.EventData", "eventData", Message.SUCCESS, "x=a+b;y=c+d");
        return userService.getUserName(id);
    }

    @Override
    public String manualTransaction(String param) {
        Transaction t = Cat.newTransaction("Demo.Transaction", "manualTransaction");
        try {
            // do business
            TimeUnit.SECONDS.sleep(3);
            Cat.logEvent("Demo.Transaction.InnerEvent", param);
            t.setStatus(Message.SUCCESS);
        } catch (Exception e) {
            Cat.logError(e);
            t.setStatus(e);
        } finally {
            t.complete();
        }
        return "ok";
    }

    @Override
    @CatTransaction
    public String automaticTransaction(String param) {
        log.info("automaticTransaction, {}", param);
        try {
            TimeUnit.SECONDS.sleep(Long.parseLong(param));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public String trace(String param) {
        String userName = userService.getUserName(1L);
        UserRequest userRequest = new UserRequest();
        userRequest.setNickName(param);
        UserResponse userResponse = userClientApi.findUser(userRequest);
        return userResponse.getUserName();
    }

    @Override
    public String metric(String param) {
        Cat.logMetricForCount("demo.metric.count", 10);
        Cat.logMetricForDuration("demo.metric.time", 1000);
        return "ok";
    }

    @Override
    public void logError() {
        try {
            int x = 1 / 0;
        } catch (Exception e) {
            log.error("demo-exception-by-zero"); //不会上报
            log.error("demo-exception-with-throwable", e);
        }
    }
}
