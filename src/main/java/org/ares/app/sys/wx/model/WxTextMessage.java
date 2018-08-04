package org.ares.app.sys.wx.model;

import static org.ares.app.sys.wx.conf.WxParams.WX_MSG_XML_ROOT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.ares.app.common.xml.adapter.CDataAdapter;

@XmlRootElement(name = WX_MSG_XML_ROOT)
public class WxTextMessage extends WxBaseMessage {

	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "Content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;// 文本消息内容

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxTextMessage [");
		if (content != null) {
			builder.append("content=");
			builder.append(content);
			builder.append(", ");
		}
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}
