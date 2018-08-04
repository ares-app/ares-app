package org.ares.app.sys.wx.model;

import static org.ares.app.sys.wx.conf.WxParams.WX_MSG_XML_ROOT;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_CONTENT;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_CREATETIME;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_DESCRIPTION;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_EVENT;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_EVENTKEY;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_FORMAT;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_FROMUSERNAME;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_LABEL;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_LATITUDE;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_LOCATION_X;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_LOCATION_Y;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_LONGITUDE;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_MEDIAID;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_MSGID;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_MSGTYPE;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_PICURL;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_PRECISION;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_SCALE;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_THUMBMEDIAID;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_TICKET;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_TITLE;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_TOUSERNAME;
import static org.ares.app.sys.wx.conf.WxParams.WX_TAG_URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = WX_MSG_XML_ROOT)
public class WxModel {

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	@XmlElement(name = WX_TAG_TOUSERNAME)
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@XmlElement(name = WX_TAG_FROMUSERNAME)
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@XmlElement(name = WX_TAG_CREATETIME)
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@XmlElement(name = WX_TAG_MSGTYPE)
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@XmlElement(name = WX_TAG_CONTENT)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@XmlElement(name = WX_TAG_MSGID)
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@XmlElement(name = WX_TAG_PICURL)
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@XmlElement(name = WX_TAG_MEDIAID)
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@XmlElement(name = WX_TAG_FORMAT)
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@XmlElement(name = WX_TAG_THUMBMEDIAID)
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@XmlElement(name = WX_TAG_LOCATION_X)
	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	@XmlElement(name = WX_TAG_LOCATION_Y)
	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	@XmlElement(name = WX_TAG_SCALE)
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@XmlElement(name = WX_TAG_LABEL)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@XmlElement(name = WX_TAG_TITLE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = WX_TAG_DESCRIPTION)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = WX_TAG_URL)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlElement(name = WX_TAG_EVENT)
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@XmlElement(name = WX_TAG_EVENTKEY)
	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@XmlElement(name = WX_TAG_TICKET)
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@XmlElement(name = WX_TAG_LATITUDE)
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@XmlElement(name = WX_TAG_LONGITUDE)
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@XmlElement(name = WX_TAG_PRECISION)
	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxModel [");
		if (signature != null) {
			builder.append("signature=");
			builder.append(signature);
			builder.append(", ");
		}
		if (timestamp != null) {
			builder.append("timestamp=");
			builder.append(timestamp);
			builder.append(", ");
		}
		if (nonce != null) {
			builder.append("nonce=");
			builder.append(nonce);
			builder.append(", ");
		}
		if (echostr != null) {
			builder.append("echostr=");
			builder.append(echostr);
			builder.append(", ");
		}
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
		if (content != null) {
			builder.append("content=");
			builder.append(content);
			builder.append(", ");
		}
		if (msgId != null) {
			builder.append("msgId=");
			builder.append(msgId);
			builder.append(", ");
		}
		if (picUrl != null) {
			builder.append("picUrl=");
			builder.append(picUrl);
			builder.append(", ");
		}
		if (mediaId != null) {
			builder.append("mediaId=");
			builder.append(mediaId);
			builder.append(", ");
		}
		if (format != null) {
			builder.append("format=");
			builder.append(format);
			builder.append(", ");
		}
		if (thumbMediaId != null) {
			builder.append("thumbMediaId=");
			builder.append(thumbMediaId);
			builder.append(", ");
		}
		if (location_X != null) {
			builder.append("location_X=");
			builder.append(location_X);
			builder.append(", ");
		}
		if (location_Y != null) {
			builder.append("location_Y=");
			builder.append(location_Y);
			builder.append(", ");
		}
		if (scale != null) {
			builder.append("scale=");
			builder.append(scale);
			builder.append(", ");
		}
		if (label != null) {
			builder.append("label=");
			builder.append(label);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (url != null) {
			builder.append("url=");
			builder.append(url);
			builder.append(", ");
		}
		if (event != null) {
			builder.append("event=");
			builder.append(event);
			builder.append(", ");
		}
		if (eventKey != null) {
			builder.append("eventKey=");
			builder.append(eventKey);
			builder.append(", ");
		}
		if (ticket != null) {
			builder.append("ticket=");
			builder.append(ticket);
			builder.append(", ");
		}
		if (latitude != null) {
			builder.append("latitude=");
			builder.append(latitude);
			builder.append(", ");
		}
		if (longitude != null) {
			builder.append("longitude=");
			builder.append(longitude);
			builder.append(", ");
		}
		if (precision != null) {
			builder.append("precision=");
			builder.append(precision);
		}
		builder.append("]");
		return builder.toString();
	}



	/* ################### 接入用 ######################## */
	private String signature;// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String timestamp;// 时间戳
	private String nonce;// 随机数
	private String echostr;// 随机字符串
	/* ################### 接入用 ######################## */

	/* ################### 接收消息 ######################## */

	private String toUserName;// 开发者微信号
	private String fromUserName;// 发送方帐号（一个OpenID）
	private Long createTime;// 消息创建时间 整型
	private String msgType;// msg:{text,image,voice,video,shortvideo,location,link}	enent:{subscribe,unsubscribe,SCAN,LOCATION,CLICK,VIEW}
	private String content;// 文本消息内容
	private Long msgId;// 消息id，64位整型

	private String picUrl;// 图片链接

	private String mediaId;// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据

	private String format;// 语音格式，如amr，speex等

	private String thumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据

	private String location_X;// 地理位置维度
	private String location_Y;// 地理位置经度
	private String scale;// 地图缩放大小
	private String label;// 地理位置信息

	private String title;// 消息标题
	private String description;// 消息描述
	private String url;// 消息链接
	
	private String event;//事件类型，subscribe(订阅)、unsubscribe(取消订阅)	SCAN
	
	private String eventKey;//事件KEY值，qrscene_为前缀，后面为二维码的参数值
	private String ticket;//二维码的ticket，可用来换取二维码图片
	
	private String latitude;//地理位置纬度
	private String longitude;//地理位置经度
	private String precision;//地理位置精度

	/* ################### 接收消息 ######################## */
	
	
}
