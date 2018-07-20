package com.fhl.activitymq.activityma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @Author harlen
 * @Date 2018/7/10 17:42
 * @Description: ${TODO}
 **/
public class TopicListener implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @SuppressWarnings("unchecked")
    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            String text = msg.getText();
            System.out.println("TopicListener接收到消息："+text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}