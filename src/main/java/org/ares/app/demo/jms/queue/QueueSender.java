package org.ares.app.demo.jms.queue;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class QueueSender {
	
	/**
	 * @param queueName 队列名称
	 * @param message 消息内容
	 */
	public void send(String queueName,final String message){
	    jtq.send(queueName, new MessageCreator() {
	        public Message createMessage(Session session) throws JMSException {
	            return session.createTextMessage(message);
	        }
	    });
	    
	    jtq.convertAndSend("[ly]--"+message);
	}
	
	@Inject //@Qualifier("jmsQueueTemplate")
	private JmsTemplate jtq;
	 
}

