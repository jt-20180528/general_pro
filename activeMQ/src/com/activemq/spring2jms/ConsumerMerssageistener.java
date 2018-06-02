package com.activemq.spring2jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMerssageistener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage msg=(TextMessage)message;
			System.out.println("接受到的消息："+msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
