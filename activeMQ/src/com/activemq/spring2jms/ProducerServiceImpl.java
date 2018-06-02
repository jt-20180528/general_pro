package com.activemq.spring2jms;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProducerServiceImpl implements ProducerService{

	@Resource
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(Destination destination,final String message){
		/*jmsTemplate.send(destination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				System.out.println("发送消息："+message);
				return session.createTextMessage(message);
			}
		});*/
		
	}

}
