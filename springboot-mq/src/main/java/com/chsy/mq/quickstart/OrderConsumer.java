package com.chsy.mq.quickstart;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import java.io.UnsupportedEncodingException;

public class OrderConsumer {
    public static void main(String[] args) throws Exception {
        // 1、创建DefaultMQPushConsumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo_consumer_order_group");

        // 2、设置namesrv地址
        consumer.setNamesrvAddr("10.210.40.59:9876");

        // 3、设置消息拉取最大数
        consumer.setConsumeMessageBatchMaxSize(2);

        // 4、设置subscribe，这里是要读取订单主题信息
        consumer.subscribe("Topic_demo", //指定要消费的主题
                "*"); //过滤规则

        // 5、创建消息监听MessageListener
        consumer.setMessageListener((MessageListenerOrderly) (list, consumeOrderlyContext) -> {
            // 6、获取消息信息
            // 迭代消息信息
            for (MessageExt msg : list) {
                // 获取主题
                String topic = msg.getTopic();
                // 获取标签
                String tags = msg.getTags();
                try {
                    // 获取信息
                    byte[] body = msg.getBody();
                    String result = new String(body, RemotingHelper.DEFAULT_CHARSET);
                    System.out.println("Order Consumer消费信息----topic: " + topic + ", tags: " + tags + ", result: " + result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // 消息重试
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }
            // 7、返回消息读取状态
            return ConsumeOrderlyStatus.SUCCESS;
        });
        // 开启consumer
        consumer.start();
    }
}
