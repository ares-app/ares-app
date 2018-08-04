/*
 * @(#) JSONUtils.java 1.00 2012-5-31
 *
 * (c) Copyright 2012 Neusoft IIT R&D Center
 *
 *   FILENAME     :  JSONUtils.java
 *   PACKAGE      :  org.ares.app.sfgl.util
 *   CREATE DATE  :  2012-5-31
 *   AUTHOR       :  huhp
 *   MODIFIED BY  :  
 *   DESCRIPTION  :  Json工具类
 *
 */
package org.ares.app.common.util;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONUtils {
	
private final static ObjectMapper mapper = new ObjectMapper();
	
	/***
	 * 将对象序列化为JSON文本
	 */
	public static String toJSONString(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象转换为JSON字符串
	 */
	public static String objectToJson(Object obj) {
		String json = null;

		StringWriter sw = new StringWriter();
		try {
			new ObjectMapper().writeValue(sw, obj);
			json = sw.toString();
		} catch (Exception e) {
		} finally {
			try {
				sw.close();
			} catch (IOException e) {
			}
		}
		return json;
	}
	
	/**
	 * JSON字符串转换为对象
	 */
	public static Object jsonToObject(String json, Class<?> cls) throws Exception {
		Object obj = new ObjectMapper().readValue(json, cls);
		return obj;
	}
	/***
	 * 将JSON文本转化为java对象
	 */
	public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 将JSON文本转化为java对象
	 */
	@SuppressWarnings("unchecked")
	public static <C, E> C fromJsonString(String jsonString, Class<C> collectionClazz, Class<E> elementClazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClazz, elementClazz);
			return (C)mapper.readValue(jsonString, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 将JSON文本转化为java对象
	 */
	@SuppressWarnings("unchecked")
	public static <C, K, E> C fromJsonString(String jsonString, Class<C> collectionClazz, Class<K> keyClazz, Class<E> elementClazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClazz, keyClazz, elementClazz);
			return (C)mapper.readValue(jsonString, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
