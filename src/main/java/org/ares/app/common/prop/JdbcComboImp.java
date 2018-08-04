package org.ares.app.common.prop;

import static org.ares.app.common.conf.Params.APPLICATION_PARAM_KV;
import static org.ares.app.common.conf.Params.JSON_COMBO_NAME;
import static org.ares.app.common.conf.Params.JSON_COMBO_VALUE;
import static org.ares.app.common.util.Util.isEmpty;
import static org.ares.app.common.util.Util.isSimpleSql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.ContextLoader;

/**
 * s-fsc.xml
 *  <property name="immu_tab_sqls">
		<list><value>select  'key1' name, fa2 value,fa3 content from t1</value></list>
	</property>
 * @author ly
 *
 */
public class JdbcComboImp implements ICombo {

	@PostConstruct
	public void init(){

		try{
			params.clear();
			loadImmuCode();
			loadImmuTabs();
		}catch(Exception e){
			e.printStackTrace();
			log.error("code table init error!");
		}
		
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
	
	/**
	 * code table
	 */
	void loadImmuCode(){
		if(!isSimpleSql(immu_code_sql))
			return;
		String sql=immu_code_sql;
		List<Map<String,Object>> list=jt.queryForList(sql);
		List<Map<String,String>> itemList=null;
		for(Map<String,Object> codeMap:list){
			String key=(String)codeMap.get("name");
			if(params.get(key)==null){
				itemList=new ArrayList<Map<String,String>>();
				params.put(key, itemList);
			}
			Map<String,String> m=new LinkedHashMap<String, String>();
			String v=(String)codeMap.get("value");
			String c=(String)codeMap.get("content");
			m.put(JSON_COMBO_NAME, c);
			m.put(JSON_COMBO_VALUE, v);
			itemList.add(m);
		}
	}

	/**
	 * normal table
	 */
	void loadImmuTabs(){
		if(isEmpty(immu_tab_sqls)||immu_tab_sqls.length==0)
			return;
		for(String sql:immu_tab_sqls){
			List<Map<String,Object>> list=jt.queryForList(sql);
			List<Map<String,String>> itemList=new ArrayList<Map<String,String>>();
			String key=null;
			for(Map<String,Object> codeMap:list){
				Map<String,String> m=new LinkedHashMap<String, String>();
				String v=""+codeMap.get("value");
				String c=""+codeMap.get("context");
				if (key==null) key=""+codeMap.get("name");
				m.put(JSON_COMBO_NAME, c);
				m.put(JSON_COMBO_VALUE, v);
				itemList.add(m);
			}
			params.put(key, itemList);
		}
		
	}

	@Override
	public Map<String, List<Map<String, String>>> getParams() {
		return params;
	}

	@Override
	public void addProp(String key, List<Map<String, String>> val) {
		params.put(key, val);
	}

	@Override
	public void refreshParams() {
		init();
	}
	
	public void setImmu_code_sql(String immu_code_sql) {
		this.immu_code_sql = immu_code_sql;
	}
	

	public void setImmu_tab_sqls(String[] immu_tab_sqls) {
		this.immu_tab_sqls = immu_tab_sqls;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	private String immu_code_sql; // select name,value,content from t
	private String[] immu_tab_sqls={};//{select  'key1' name, fa2 value,fa3 content from t1,select 'key2' name,fb2 value,fb3 content from t2}
	private JdbcTemplate jt;
	private static final Map<String,List<Map<String,String>>> params=new HashMap<String,List<Map<String,String>>>();
	static Logger log = Logger.getLogger(JdbcComboImp.class);
}
