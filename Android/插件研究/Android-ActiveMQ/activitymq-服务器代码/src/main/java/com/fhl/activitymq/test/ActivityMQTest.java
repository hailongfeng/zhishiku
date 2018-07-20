package com.fhl.activitymq.test;

import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

public class ActivityMQTest extends BasicTest {

	@Resource(name = "demoQueueDestination")
	private Destination queueDestination;
	@Resource(name = "demoTopicDestination")
	private Destination topicDestination;
	@Resource(name="jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;
	@Resource(name="jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;


	@Test
	public void sendMessage() {
		jmsQueueTemplate.send(queueDestination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				Message message=session.createTextMessage("hello world！！！Queue"+System.currentTimeMillis());
//				message.setStringProperty();
//				message.setStringProperty("PTP_CLIENTID", clientId);
				return message;
			}
		});
		jmsTopicTemplate.send(topicDestination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("hello world！！！Topic"+System.currentTimeMillis());
			}
		});

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
