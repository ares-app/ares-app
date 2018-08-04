package org.ares.app.demo.jms.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class QueueReceiver implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		TextMessage tmsg=(TextMessage)msg;
		try {
			log.info(tmsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

	static final Logger log=Logger.getLogger(QueueReceiver.class);
}
