package com.upuphone.cloudplatform.demo.business.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/1/18 11:16
 */
@Slf4j
@Component
//@RocketMQMessageListener(topic = "${mq.consumer.topic}", consumerGroup = "${mq.consumer.group}")
public class RocketMQConsumerDemo implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info(message.toString());
    }
}
