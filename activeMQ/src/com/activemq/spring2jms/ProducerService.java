package com.activemq.spring2jms;

import javax.jms.Destination;

public interface ProducerService {

	public void sendMessage(Destination destination,final String message);
}
