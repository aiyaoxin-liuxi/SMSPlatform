package com.sms.test.MQ.rocketMq;

/**
 * 测试RocketMQ的消费
 * 
 * @author 刘熙
 * 
 */
public class RocketMQConsumerTest {

    public static void main(String[] args) {

        String mqNameServer = "127.0.0.1:8081";
        String mqTopics = "MQ-MSG-TOPICS-TEST";

        String consumerMqGroupName = "CONSUMER-MQ-GROUP";
        RocketMQListener mqListener = new RocketMQListener();
        RocketMQConsumer mqConsumer = new RocketMQConsumer(mqListener, mqNameServer, consumerMqGroupName, mqTopics);
        mqConsumer.init();

        try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
