package org.ares.app.common.prop;

import java.util.List;
import java.util.Map;

public interface ICombo {

	public Map<String,List<Map<String,String>>> getParams();
	
	public void addProp(String key,List<Map<String,String>> val);
	
	public void refreshParams();
	
}
