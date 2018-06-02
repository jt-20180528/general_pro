package com.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	private static ConnectionFactory connectionFactory;
	private static Connection connection;
	private static Session session;
	private static Destination destination;
	private static MessageConsumer consumer;
	
	public static void main(String[] args) {
		try {
			connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, 
					ActiveMQConnectionFactory.DEFAULT_PASSWORD, 
					"");
			connection=connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createQueue("FirstQueue");
			consumer=session.createConsumer(destination);
			session.commit();
			while(true){
				TextMessage message=(TextMessage) consumer.receive(1000);
				if(message!=null){
					System.out.println(message.getText());
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}finally{
			closeConnection();
			closeProduce();
			closeSession();
		}
	}
	
	public static void closeConnection(){
		try {
			if(connection!=null){
				connection.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void closeSession(){
		try {
			if(session!=null){
				session.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void closeProduce(){
		try {
			if(consumer!=null){
				consumer.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
