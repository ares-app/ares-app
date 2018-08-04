package org.ares.app.demo.action;

import java.io.StringReader;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.ares.app.demo.model.WxModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test/t-rest.xml")
public class ActionRestTest {

	/**
	 * RestTemplate restTemplate = new RestTemplate();


    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

    //This JAXB Message converter is intended to marshal an XML message over HTTP.
    //However, I find this converter is not doing the intended function.

    Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
    List<MediaType> mediaTypes = new ArrayList<MediaType>();
    mediaTypes.add(MediaType.TEXT_HTML);
    jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);
	messageConverters.add(jaxbMessageConverter);
	restTemplate.setMessageConverters(messageConverters);
	restTemplate.postForLocation("http://localhost:8080/RecipeProject/restCallConsumer", "<add><somefield></somefield></add>",String.class);
	 */
	
	//@Test	
	public void testXML() {
		String url="http://localhost:8080/ares/wx/receive.do";
		HttpHeaders head = new HttpHeaders();
		head.setContentType(MediaType.TEXT_PLAIN);
		MultiValueMap<String, String> md = new LinkedMultiValueMap<String, String>();
		md.add("Content", "con");
		//HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(md, head);
		/*HttpEntity<String> request = new HttpEntity<String>(head);*/
		
		/*ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, request, String.class);
		log.info(responseEntity.getBody());*/
		Object v=rest.postForLocation(url,
				"<xml><ToUserName>ly</ToUserName><FromUserName>wx</FromUserName><Content>ctx</Content></xml>", String.class);
		log.info(v);
	}

	@Test  
	public void t() throws JAXBException{  
	    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>11</age><height>h</height><name>zhang</name><width>w</width></student>";  
	    xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><xml><ToUserName>ly</ToUserName><FromUserName>wx</FromUserName><content>ctx</content></xml>";
	    JAXBContext jc = JAXBContext.newInstance(WxModel.class);  
	    Unmarshaller unmar = jc.createUnmarshaller();  
	    WxModel m = (WxModel) unmar.unmarshal(new StringReader(xml));  
	    System.out.println(m.getContent());  
	}  
	
	@Inject private RestTemplate rest;
	private static Logger log = Logger.getLogger(ActionRestTest.class);

}
