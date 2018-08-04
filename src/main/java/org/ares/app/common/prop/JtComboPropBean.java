package org.ares.app.common.prop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.ContextLoader;

import org.ares.app.common.util.Util;

import static org.ares.app.common.conf.Params.*;

public class JtComboPropBean implements ComboProperty {

	@PostConstruct
	public void init(){
		params.clear();
		if(Util.isNotEmpty(dynaTabs))
			for(String t:dynaTabs){
				String[] tab=t.split(",");
				if(tab.length==3)
					initDynaTable(tab[0],tab[1],tab[2]);
				else if(tab.length==5)
					initDynaTable(tab[0],tab[1],tab[2],tab[3],tab[4]);
			}
		
		initStaticTable();
		
		new Thread(){
			public void run(){
				while(true){
					if(ContextLoader.getCurrentWebApplicationContext()==null){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						ContextLoader.getCurrentWebApplicationContext().getServletContext().setAttribute(APPLICATION_PARAM_KV, params);
						break;
					}
				}
			}
		}.start();
	}
	
	void initDynaTable(String entityName,String value,String context){
		initDynaTable(entityName,value,context,"",value);
	}
	
	private void initDynaTable(String entityName,String value,String context,String where,String propertyAlias){
		
		if(!"".equals(where))
			where=" where "+where;
		String hql="select * from "+entityName+where;
		List<Map<String,Object>> list=jt.queryForList(hql);
		
		List<Map<String,String>> itemList=new ArrayList<Map<String,String>>();
		for(Map<String,Object> entityMap:list){
			Map<String,String> m=new LinkedHashMap<String, String>();
			String v=""+entityMap.get(value);
			String c=""+entityMap.get(context);
			m.put(JSON_COMBO_NAME, c);
			m.put(JSON_COMBO_VALUE, v);
			itemList.add(m);
		}
		params.put(propertyAlias, itemList);
	}
	
	private void initStaticTable(){
		String order_ii=codes.getProperty("value")+" asc";
		if(codes.get("order")!=null)
			order_ii=codes.get("order")+"";
		String hql="select * from "+codes.getProperty("entity")+" order by "+codes.getProperty("name")+","+order_ii;
		List<Map<String,Object>> list=jt.queryForList(hql);
				
		List<Map<String,String>> itemList=null;
		for(Map<String,Object> entityMap:list){
			String key=(String)entityMap.get(codes.getProperty("name"));
			if(params.get(key)==null){
				itemList=new ArrayList<Map<String,String>>();
				params.put(key, itemList);
			}
			Map<String,String> m=new LinkedHashMap<String, String>();
			String v=(String)entityMap.get(codes.getProperty("value"));
			String c=(String)entityMap.get(codes.getProperty("content"));
			m.put(JSON_COMBO_NAME, c);
			m.put(JSON_COMBO_VALUE, v);
			itemList.add(m);
		}
	}

	public String[] getDynaTabs() {
		return dynaTabs;
	}

	public void setDynaTabs(String[] dynaTabs) {
		this.dynaTabs = dynaTabs;
	}

	public Properties getCodes() {
		return codes;
	}

	public void setCodes(Properties codes) {
		this.codes = codes;
	}
	
	public Map<String,List<Map<String,String>>> getParams() {
		return params;
	}
	
	@Override
	public void addProp(String key,List<Map<String,String>> val) {
		params.put(key, val);
	}

	@Override
	public void refreshParams() {
		init();
	}
	
	private static final Map<String,List<Map<String,String>>> params=new HashMap<String,List<Map<String,String>>>();
	private Properties codes;//entity,name,value,content
	private String[] dynaTabs;
	@Resource private JdbcTemplate jt;

}
