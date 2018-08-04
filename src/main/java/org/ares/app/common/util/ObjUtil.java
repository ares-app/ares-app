package org.ares.app.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ObjUtil {

	public static void main(String[] args) {
		// testgetFiledName();
		//testgetFiledsInfo();
		//ObjUtil obju = new ObjUtil();
		//obju.testgetFiledsInfo();
	}

	static void testgetFiledName() {
		String[] arr = getFiledName("");
		for (String prop : arr) {
			System.out.println(prop);
		}
	}
	public static void setFiledValue(Object o,String filedname,Object value){
		try {
			Field f = o.getClass().getDeclaredField(filedname);
			f.setAccessible(true);
			f.set(o, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String[] getFiledName(Object o) {
		try {
			Field[] fields = o.getClass().getDeclaredFields();
			String[] fieldNames = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName().toLowerCase();
			}
			return fieldNames;
		} catch (SecurityException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return null;
	}

	/**
	 * 根据属性名获取属性值
	 * */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 * */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List<Map> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			if(!"serialVersionUID".equals(fields[i].getName())){//序列号属性不读取
				infoMap.put("type", fields[i].getType().toString());
				infoMap.put("name", fields[i].getName());
				infoMap.put("value", getFieldValueByName(fields[i].getName(), o));	
			}
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 获取对象的所有属性值，返回一个对象数组
	 * */
	public static Object[] getFiledValues(Object o) {
		String[] fieldNames = getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String)
			return ((String) obj).trim().length() == 0;
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static Object createObject(String clazz) {
		Object obj = null;
		try {
			obj = Class.forName(clazz).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static HashMap<String, String> getMaps(String[] s, String[] d) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < s.length; i++) {
			map.put(s[i], d[i]);
		}
		return map;
	}
}