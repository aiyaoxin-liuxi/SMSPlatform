package com.sms.test.MQ.rocketMq;

import com.alibaba.rocketmq.common.message.Message;

/**
 * MQ的生产者测试类
 * 
 * @author 刘熙
 * 
 */
public class RocketMQProducerTest {

    public static void main(String[] args) {

        String mqNameServer = "127.0.0.1:8081";
        String mqTopics = "MQ-MSG-TOPICS-TEST";

        String producerMqGroupName = "PRODUCER-MQ-GROUP";
        RocketMQProducer mqProducer = new RocketMQProducer(mqNameServer, producerMqGroupName, mqTopics);
        mqProducer.init();

        for (int i = 0; i < 5; i++) {

            Message message = new Message();
            message.setBody(("I send message to RocketMQ " + i).getBytes());
            mqProducer.send(message);
        }

    }

}
