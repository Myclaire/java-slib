package com.chsy.mq.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * // 1、创建DefaultMQProducer
 * // 2、设置Namesrv地址
 * // 3、开启DefaultMQProducer
 * // 4、创建消息Message
 * // 5、发送消息
 * // 6、关闭DefaultMQProducer
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        // 1、创建DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("demo_produce_group");

        // 2、设置Namesrv地址
        producer.setNamesrvAddr("10.210.40.59:9876");

        // 3、开启DefaultMQProducer
        producer.start();

        // 4、创建消息Message topic、tags、keys、body
        Message message = new Message("Topic_demo",//主题
                "Tags",//主要用于消息过滤
                "Keys_1",//消息的唯一值
                "hello".getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 5、发送消息
        SendResult result = producer.send(message);
        System.out.println(result);

        // 6、关闭DefaultMQProducer
        producer.shutdown();
    }
}
