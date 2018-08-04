package org.ares.app.common.prop;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface ComboProperty {

	public void setDynaTabs(String[] dynaTabs);

	public Properties getCodes();

	public void setCodes(Properties codes);
	
	public Map<String,List<Map<String,String>>> getParams();
	
	public void addProp(String key,List<Map<String,String>> val);
	
	public void refreshParams();
	
}
