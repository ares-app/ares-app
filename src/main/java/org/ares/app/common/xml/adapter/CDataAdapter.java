package org.ares.app.common.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDataAdapter extends XmlAdapter<String, String> {
	@Override
	public String marshal(String str) throws Exception {
		return "<![CDATA[" + str + "]]>";
	}

	@Override
	public String unmarshal(String str) throws Exception {
		return str;
	}
}