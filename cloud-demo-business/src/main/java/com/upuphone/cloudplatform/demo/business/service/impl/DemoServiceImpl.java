package com.upuphone.cloudplatform.demo.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.upuphone.cloudplatform.demo.api.UserClientApi;
import com.upuphone.cloudplatform.demo.api.dto.request.UserRequest;
import com.upuphone.cloudplatform.demo.api.dto.response.UserResponse;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import com.upuphone.cloudplatform.demo.business.converter.DemoConverter;
import com.upuphone.cloudplatform.demo.business.service.DemoService;
import com.upuphone.cloudplatform.demo.dao.entity.Demo;
import com.upuphone.cloudplatform.demo.dao.entity.User;
import com.upuphone.cloudplatform.demo.dao.mapper.DemoMapper;
import com.upuphone.cloudplatform.demo.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/20 13:46
 */
@Slf4j
@Service
public class DemoServiceImpl extends ServiceImpl<UserMapper, User> implements DemoService {

    @Resource
    private DemoMapper demoMapper;

    @Resource
    private UserClientApi userClientApi;

    @Value("${demo.enabled:false}")
    private boolean demoEnabled;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public DemoServiceImpl() {
    }

    @Override
    public String demoExecute(String args) {
        log.info("begin demo execute, args is {}, apollo config key demo.enabled is {}", args, demoEnabled);
        Demo demo = demoMapper.selectById(Long.valueOf(args));
        String userName = userClientApi.getUserName(Long.valueOf(args));
        UserRequest userRequest = new UserRequest();
        userRequest.setNickName("leon");
        UserResponse userResponse = userClientApi.findUser(userRequest);
        String result = String.format("hello, args is = %s, sql query result is = %s, feign call result is = %s, apollo config is = %s",
                args, demo, userName, demoEnabled);
        return result;
    }

    @Override
    public Long insert(DemoBo demoBo) {
        //dto convert po
        Demo demo = new Demo();
        demo.setUrl(demoBo.getUrl());
        demoMapper.insert(demo);
        return demo.getId();
    }

    @Override
    @Trace
    @Tags({@Tag(key = "param", value = "arg[0]"),
            @Tag(key = "param", value = "arg[1]"),
            @Tag(key = "return", value = "returnedObj")})
    public PageDTO<DemoBo> selectList(int pageNum, int pageSize) {
        log.info("select list pageNum={}, pageSize={}", pageNum, pageSize);
        Page<Demo> page = Page.of(pageNum, pageSize);
        QueryWrapper<Demo> wrapper = new QueryWrapper<>();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Page<Demo> pageResult = demoMapper.selectPage(page, wrapper);
        PageDTO<DemoBo> pageDTO = DemoConverter.INSTANCE.poPage2BoPage(pageResult);
        return pageDTO;
    }

    @Override
    public boolean delete(Long id) {
        return demoMapper.deleteById(id) > 0;
    }

    @Override
    public String redis(String args) {
        redisTemplate.opsForValue().set("fun1", args, 60, TimeUnit.SECONDS);
        DemoBo demoBo = new DemoBo();
        demoBo.setId(100L);
        demoBo.setUrl("www.baidu.com");
        redisTemplate.opsForValue().set("fun2", demoBo);
        DemoBo result = (DemoBo) redisTemplate.opsForValue().get("fun2");
        return result.getUrl();
    }

    @Override
    public String producerRocketmq(String topic) {
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload("hello, payload").build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("asyncSend, {}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("asyncSend", e);
            }
        });
        return "ok";
    }

    @Override
    public String consumerRocketmq() {
        DefaultLitePullConsumer consumer = rocketMQTemplate.getConsumer();
        List<MessageExt> poll = consumer.poll();
        List<MessageExt> messageExtList = rocketMQTemplate.receive(MessageExt.class);
        return "ok";
    }

    public String CheckStyle() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 6; k++) {
                    System.out.println("three loop for check style");
                }
            }
        }
        String testA = "testA";
        String testB = "testB";
        if (testA == testB) {}
        return "checkStyle";
    }
}
