package org.ares.app.demo.action;

import static org.ares.app.common.conf.Params.OAPI_ACCESS_KEY;
import static org.ares.app.common.conf.Params.OAPI_SECRET_KEY;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_NAME;
import static org.ares.app.common.conf.Params.OAPI_SERVICE_URL;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.ares.app.common.oapi.client.mapsrv.OApiMapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test/t-oapic.xml")
public class UserActionRestTest {

	/**
	 * OAPI_ACCESS_KEY OAPI_REQUEST_IP OAPI_REQUEST_TIMESTAMP OAPI_SECRET_KEY
	 * OAPI_SERVICE_URL OAPI_SERVICE_NAME
	 */

	@Test
	public void testOapiGetUser() {
		Map<String, Object> param = new HashMap<>();
		param.put(OAPI_ACCESS_KEY, "oapi");
		param.put(OAPI_SECRET_KEY, "oapi");
		param.put(OAPI_SERVICE_URL, "http://192.168.0.118:8080/ares/oapi/xuserlist.do");
		param.put(OAPI_SERVICE_URL, "http://192.168.0.118:8080/ares/oapi/userlist.do");
		param.put(OAPI_SERVICE_NAME, "user_query");
		Map<String, String> p = service.buildOApiServiceParam(param);
		String url = p.get(OAPI_SERVICE_URL);

		HttpHeaders head = new HttpHeaders();
		head.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> md = new LinkedMultiValueMap<String, String>();
		for (String k : p.keySet())
			md.add(k, p.get(k));
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(md, head);

		ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, request, String.class);
		log.info("[message body]-" + responseEntity.getBody());
		log.info("[ContentType]--" + responseEntity.getHeaders().getContentType());

		String msg = rest.postForObject(url, request, String.class);
		log.info(msg);
		Map<String, Object> r = rest.postForObject(url, request, Map.class);
		log.info(r);

	}

	@Inject private OApiMapService service;
	@Inject private RestTemplate rest;
	private static Logger log = Logger.getLogger(UserActionRestTest.class);

}
