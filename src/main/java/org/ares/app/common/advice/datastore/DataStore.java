package org.ares.app.common.advice.datastore;

import java.util.HashMap;

import org.ares.app.common.page.PageModel;

/**
 * 
 * web env data=> page,request
 * biz env data
 * save and get
 * in current thread read and write data
 * @author ly
 *
 */
public abstract class DataStore {

	// split webdata and bizdata,so => sub class implement the datasotre
	public abstract ThreadLocal<HashMap<String,Object>> getDataStore();
	
	public void put(String key,Object value){
		if(getDataStore().get()==null)
			getDataStore().set(new HashMap<String,Object>());
		getDataStore().get().put(key, value);
	}
	
	public Object get(String key){
		if(getDataStore().get()==null)
			return null;
		return getDataStore().get().get(key);
	}
	
	public void clear(){
		getDataStore().set(new HashMap<String,Object>());
	}
	
	public PageModel getCurrentPage(){
		return (PageModel)get(PAGINATION_DATA);
	}
	
	public void savePage(PageModel page){
		put(PAGINATION_DATA, page);
	}
	
	public final static String PAGINATION_DATA="current_page_data";
	
}
