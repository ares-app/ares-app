package org.ares.app.demo.jms.topic;

import java.util.Map;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TopicSender {

	public void send(String topicName,final String message){
		if(topicName!=null)
			jtt.setDefaultDestinationName(topicName);
		jtt.send(new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
	public void send(String topicName,final Map<String,Object> message){
		if(topicName!=null)
			jtt.setDefaultDestinationName(topicName);
		/*jtt.send(new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
				MapMessage r= session.createMapMessage();
				for(String k:message.keySet())
					r.setObjectProperty(k, message.get(k));
				return r;
			}
		});*/
		jtt.convertAndSend(message);
	}
	
	@Inject //@Qualifier("jmsTopicTemplate") //Autowired
	private JmsTemplate jtt;
}
