package com.activemq.test;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private static ConnectionFactory connectionFactory;
	private static Connection connection;
	private static Session session;
	private static Destination destination;
	private static MessageProducer producer;
	private static final int sendNumber=10;
	
	public static void main(String[] args) {
		try {
			connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, 
					ActiveMQConnectionFactory.DEFAULT_PASSWORD, 
					"");
			connection=connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination=session.createQueue("FirstQueue");
			producer=session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			SendMessage(session,producer);
			session.commit();
		} catch (Exception e) {
			e.getMessage();
		}finally{
			closeConnection();
			closeProduce();
			closeSession();
		}
	}
	
	public static void SendMessage(Session session,MessageProducer producer){
		try {
			for(int i=0;i<sendNumber;i++){
				TextMessage message=session.createTextMessage("发送消息-"+i);
				producer.send(message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
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
			if(producer!=null){
				producer.close();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
