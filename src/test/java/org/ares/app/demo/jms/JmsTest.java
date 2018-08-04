package org.ares.app.demo.jms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:s-jms.xml"})  
public class JmsTest {

	
	@Test
	public void test_topic_str(){
		jtq.setPubSubDomain(true);
		jtq.setDefaultDestinationName("topic.demo.str");
		jtq.convertAndSend("topic--"+LocalDate.now());
	}
	
	@Test
	public void test_topic_map(){
		jtq.setPubSubDomain(true);
		jtq.setDefaultDestinationName("topic.demo.map");
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("x", "1");
		m.put("y", "2");
		m.put("z", "3");
		jtq.convertAndSend(m);
	}
	
	@Test
	public void test_queue_str(){
		jtq.setDefaultDestinationName("queue.demo.str");
		jtq.setPubSubDomain(false);
		jtq.convertAndSend("queue");
	}
	
	@Inject private JmsTemplate jtq;
}