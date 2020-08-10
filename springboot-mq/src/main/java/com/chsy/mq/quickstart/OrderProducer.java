package com.chsy.mq.quickstart;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class OrderProducer {
    public static void main(String[] args) throws Exception {
        // 1、创建DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("demo_produce_order_group");

        // 2、设置Namesrv地址
        producer.setNamesrvAddr("10.210.40.59:9876");

        // 3、开启DefaultMQProducer
        producer.start();

        // 4、连续创建消息Message topic、tags、keys、body
        for (int i = 0; i < 5; i++) {
            Message message = new Message("Topic_demo",//主题
                    "Tags",//主要用于消息过滤
                    "Keys_" + i,//消息的唯一值
                    ("hello mq_" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

            // 5、发送消息
            // 第一个参数：发送的消息信息
            // 第二个参数: 选择指定的消息队列对象（会将所有的消息队列传进来）
            // 第三个参数：指定对应的队列下标
            SendResult result = producer.send(message,
                    (List<MessageQueue> list, Message msg, Object arg) -> {
                        // 获取队列下标
                        Integer index = (Integer) arg;
                        return list.get(index);
                    },
                    1
            );
            System.out.println(result);
        }

        // 6、关闭DefaultMQProducer
        producer.shutdown();
    }
}
