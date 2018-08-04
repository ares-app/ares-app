package org.ares.app.demo.sms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ares.app.common.sms.aliyu.AliyuShortMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:s-sms.xml"})
public class AliyuSmsTest {
	
	@Test
	public void testGetUsers(){
		Map<String,String> param=new HashMap<>();
		param.put(AliyuShortMessage.SHORT_MSG_ALIYU_EXTEND, "extend");
		param.put(AliyuShortMessage.SHORT_MSG_ALIYU_REC_NUM, "13889406827");
		param.put(AliyuShortMessage.SHORT_MSG_ALIYU_SMS_PARAM_STRING, "{vercode:'abc'}");
		sms.sendShortMsg(param);
	}
	
	@Resource  AliyuShortMessage sms;

}
