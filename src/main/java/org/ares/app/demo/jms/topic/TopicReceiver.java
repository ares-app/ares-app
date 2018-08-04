package org.ares.app.demo.jms.topic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class TopicReceiver implements MessageListener {

	
	@Override
	public void onMessage(Message msg) {
		try {
			log.info("["+sdf.format(new Date(System.currentTimeMillis()))+"]--"+msg);
			if(msg instanceof TextMessage){
				TextMessage tmsg=(TextMessage)msg;
				log.info(tmsg.getText());
			}
			if(msg instanceof MapMessage){
				MapMessage mmsg=(MapMessage)msg;
				Enumeration<String> en=mmsg.getPropertyNames();
				while(en.hasMoreElements()){
					String k=en.nextElement();
					log.info("{"+k+" -> "+mmsg.getObjectProperty(k)+"}");
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

	static final Logger log=Logger.getLogger(TopicReceiver.class);
	static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
}
