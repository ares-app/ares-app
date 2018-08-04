package org.ares.app.sys.wx.model;

import static org.ares.app.sys.wx.conf.WxParams.WX_MSG_XML_ROOT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.ares.app.common.xml.adapter.CDataAdapter;

@XmlRootElement(name = WX_MSG_XML_ROOT)
public class WxBaseMessage implements WxMessage {

	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@XmlElement(name = "CreateTime")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@XmlElement(name = "MsgId")
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxBaseMessage [");
		if (toUserName != null) {
			builder.append("toUserName=");
			builder.append(toUserName);
			builder.append(", ");
		}
		if (fromUserName != null) {
			builder.append("fromUserName=");
			builder.append(fromUserName);
			builder.append(", ");
		}
		if (createTime != null) {
			builder.append("createTime=");
			builder.append(createTime);
			builder.append(", ");
		}
		if (msgType != null) {
			builder.append("msgType=");
			builder.append(msgType);
			builder.append(", ");
		}
		if (msgId != null) {
			builder.append("msgId=");
			builder.append(msgId);
		}
		builder.append("]");
		return builder.toString();
	}

	private String toUserName;// 开发者微信号
	private String fromUserName;// 发送方帐号（一个OpenID）
	private Long createTime;// 消息创建时间 整型
	private String msgType;// msg:{text,image,voice,video,shortvideo,location,link}	enent:{subscribe,unsubscribe,SCAN,LOCATION,CLICK,VIEW}
	private Long msgId;// 消息id，64位整型
	
}
