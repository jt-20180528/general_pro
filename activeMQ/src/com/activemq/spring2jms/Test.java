package com.activemq.spring2jms;

import javax.jms.Destination;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("activemq.xml")
public class Test {

	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;
	
	@Autowired
	private static ProducerService producerService;
	
	@org.junit.Test
	public void testSend(){
		for(int i=1;i<=10;i++){
			producerService.sendMessage(destination, "hello,hello-"+i);
		}
	}
}
