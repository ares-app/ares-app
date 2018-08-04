/**
 * @author ly
 */
package org.ares.app.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Util {

	/**
	 * Web环境下判断对象是否为空
	 * 字符串长度为零被定义为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null)
			return true;
		if(obj instanceof String)
			return ((String)obj).trim().length()==0;
		return false;
	} 
	
	/**
	 * 对象是否不为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	/**
	 * 按yyyy-MM-dd格式返回日期
	 * @return
	 */
	public static String getLocalDate(){
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 按指定格式返回日期串
	 * @param format
	 * @return
	 */
	public static String getDate(String format){
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static String jsonp(String json){
		return jsonp("jsoncallback",json);
	}
	
	/**
	 * "jsoncallback({'success':'true','a':'1'})";
	 * @param prefix
	 * @param json
	 * @return
	 */
	public static String jsonp(String prefix,String json){
		return prefix+"("+json+")";
	}
	
	/**
	 * 根据给定的类的全路径返回一个新的对象
	 * @param clazz
	 * @return
	 */
	public static Object createObject(String clazz){
		Object obj=null;
		try {
			obj=Class.forName(clazz).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * entity是表名还是SQL
	 * @param entity
	 * @return
	 */
	static boolean isTable(String entity){
		return entity.toLowerCase().indexOf(" from ")<0;
	}
	
	public static boolean isSimpleSql(String sql){
		if(isEmpty(sql))
			return false;
		else
			return sql.toLowerCase().matches("^\\bselect\\b.+\\bfrom\\b.+$");
	}
	
	/**
	 * conv to sql
	 * @param entity
	 * @return
	 */
	public static String toSQL(String entity){
		String r=entity;
		if(isTable(entity))
			if(entity.indexOf(",")<=0)
				r="select * from "+entity;
			else{
				final String[] rr=entity.split(",");
				r="select ";
				for(int i=1;i<rr.length;i++)
					r=r+rr[i]+",";
				r=r.substring(0, r.length()-1);
				r=r+" from "+rr[0];
			}
		return r;
	}
	
	/**
	 * 将JavaBean=>Map
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> convBeanToMap(Object bean,boolean ignoreNull)   {  
        Class type = bean.getClass();  
        Map<String,Object> r = new HashMap<String,Object>();  
        try{
        	 BeanInfo beanInfo = Introspector.getBeanInfo(type);  
        	  
             PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
             for (int i = 0; i< propertyDescriptors.length; i++) {  
                 PropertyDescriptor descriptor = propertyDescriptors[i];  
                 String propertyName = descriptor.getName();  
                 if (!propertyName.equals("class")) {  
                     Method readMethod = descriptor.getReadMethod();  
                     Object result = readMethod.invoke(bean, new Object[0]);  
                     if (result != null) {  
                         r.put(propertyName, result);  
                     } else
                    	 if(!ignoreNull)
                    		 r.put(propertyName, null);  
                 }  
             }  
        }catch(Exception e){
        	e.printStackTrace();
        }
        return r;  
    }  
	
	/**
	 * 默认忽略空值
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> convBeanToMap(Object bean)   { 
		return convBeanToMap(bean,true);
	}

	/**
	 * @param json
	 * @return
	 */
	public static Map<String, Map<String, Object>> buildMapFromJson(String json) {
		Map<String, Map<String, Object>> map = null;
		try {
			map = objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return map;
	}

	public static List<Map<String, Object>> buildListFromJson(String json) {
		List<Map<String, Object>> list = null;
		try {
			list = objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return list;
	}
	
	public static String bytesToHex(byte[] bArray,boolean upper) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toLowerCase());
		}
		return upper?sb.toString().toUpperCase():sb.toString();
	}
	
	public static String bytesToHex(byte[] bArray) {
		return bytesToHex(bArray,false);
	}
	
	public static String getGETSTR(Map<String, String[]> param){
		return getGETSTR(param,true);
	}
	
	public static String getGETSTR(Map<String, String[]> param,boolean qmark){
		String quemark="?";
		if(!qmark)
			quemark="";
		StringBuilder r=new StringBuilder(quemark);
		if(param==null ||param.isEmpty())
			return "";
		for(String k:param.keySet()){
			String[] vals=param.get(k);
			for(String val:vals){
				r.append(k).append("=").append(val).append("&");
			}
		}
		r.deleteCharAt(r.length()-1);
		return r.toString();
	}
	
	public static Map<String,String[]> cloneMap(Map<String,String[]> m){
		if(m==null)
			return null;
		Map<String,String[]> n=new HashMap<String,String[]>();
		for(String k:m.keySet())
			n.put(k, m.get(k));
		return n;
	}
	
	/**
	 * 根据值返回一个新的Map 值是数组时特殊处理：单值返回具体对象，多值返回用原来的数组构造的List 2013-3
	 * 将HashMap改为大小写不敏感的CaseInsensitiveMap
	 */
	
	
	public static Map getNaturalValueMap( Map srcMap, boolean caseInsensitive) {
		Map m = null;
		if (caseInsensitive) {
			m = new CaseInsensitiveMap();
		} else {
			m = new HashMap();
		}
		if (srcMap != null) {
			Iterator it = srcMap.keySet().iterator();
			String key = null;
			while (it.hasNext()) {
				key = (String) it.next();
				Object value = srcMap.get(key);
				if (value instanceof Object[]) {
					Object[] array = (Object[]) value;
					if (array != null && array.length == 1) {
						value = array[0];
					} else {
						value = Arrays.asList(array);
					}
				}
				if(value!=null&&!"".equals(value))
					m.put(key, value);
			}
		}
		return m;
	}

	public static Map getNaturalValueMap(Map srcMap) {
		return getNaturalValueMap(srcMap, false);
	}
	
	/**
	 * address=addr.getHostName()toString;//获得本机名称
	 * @return
	 */
	public static String getLocalIP(){
		String r=null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			r= addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	private static final ObjectMapper objectMapper=new ObjectMapper();
	
	public static void main(String[] args) throws IOException {  
        /*InputStream ins = null;  
        try {  
            URL url = new URL("http://172.23.10.47:8080/eacli/oapi/ip_addr.json");  
            URLConnection con = url.openConnection();  
            ins = con.getInputStream();  
            InputStreamReader isReader = new InputStreamReader(ins, "utf-8");  
            BufferedReader bReader = new BufferedReader(isReader);  
            StringBuffer webContent = new StringBuffer();  
            String str = null;  
            while ((str = bReader.readLine()) != null) {  
                webContent.append(str);  
            }  
            int start = webContent.indexOf("[") + 1;  
            int end = webContent.indexOf("]");  
            String r= webContent.substring(start, end);
            System.out.println(webContent);
        } finally {  
            if (ins != null) {  
                ins.close();  
            }  
        }*/  
		/*String r=null;
		try {
			//InetAddress addr = InetAddress.getLocalHost();
			r= InetAddress.getByName("localhost").getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println(r);*/
		
		String sql="select * bfrom x where 1=1 order c";
		System.out.println(sql.matches("^\\bselect\\b\\w+"));
		System.out.println(sql.matches("^\\bselect\\b.+\\bfrom\\b.+$"));
    }  
	
	
}
