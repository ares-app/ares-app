package org.ares.app.sys.wx.conf;

public class WxParams {

	public static final String WX_MSG_XML_ROOT="xml";
	
	//public static final String msgType;// msg:{text,image,voice,video,shortvideo,location,link,music,news}	enent:{subscribe,unsubscribe,SCAN,LOCATION,CLICK,VIEW}
	
	//**************************消息类型*********************************************//
	public static final String WX_MSG_TEXT_TYPE="text";
	public static final String WX_MSG_IMAGE_TYPE="image";
	public static final String WX_MSG_VOICE_TYPE="voice";
	public static final String WX_MSG_VIDEO_TYPE="video";
	public static final String WX_MSG_SHORTVIDEO_TYPE="shortvideo";
	public static final String WX_MSG_LOCATION_TYPE="location";
	public static final String WX_MSG_LINK_TYPE="link";
	public static final String WX_MSG_MUSIC_TYPE="music";
	public static final String WX_MSG_NEWS_TYPE="news";
	//**************************消息类型*********************************************//
	
	//**************************事件类型*********************************************//
	public static final String WX_EVENT_SUBSCRIBE_TYPE="subscribe";
	public static final String WX_EVENT_UNSUBSCRIBE_TYPE="unsubscribe";
	public static final String WX_EVENT_SCAN_TYPE="SCAN";
	public static final String WX_EVENT_LOCATION_TYPE="LOCATION";
	public static final String WX_EVENT_CLICK_TYPE="CLICK";
	public static final String WX_EVENT_VIEW_TYPE="VIEW";
	//**************************事件类型*********************************************//
	
	//********************************************消息，事件标签名称*********************************************//
	public static final String WX_TAG_TOUSERNAME= "ToUserName";// 开发者微信号
	public static final String WX_TAG_FROMUSERNAME="FromUserName";// 发送方帐号（一个OpenID）
	public static final String WX_TAG_CREATETIME ="CreateTime";// 消息创建时间 整型
	public static final String WX_TAG_MSGTYPE="MsgType";// msg:{text,image,voice,video,shortvideo,location,link}	enent:{subscribe,unsubscribe,SCAN,LOCATION,CLICK,VIEW}
	public static final String WX_TAG_CONTENT="Content";// 文本消息内容
	public static final String WX_TAG_MSGID="MsgId";// 消息id，64位整型

	public static final String WX_TAG_PICURL="PicUrl";// 图片链接

	public static final String WX_TAG_MEDIAID="MediaId";// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据

	public static final String WX_TAG_FORMAT="Format";// 语音格式，如amr，speex等

	public static final String WX_TAG_THUMBMEDIAID="ThumbMediaId";// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据

	public static final String WX_TAG_LOCATION_X="Location_X";// 地理位置维度
	public static final String WX_TAG_LOCATION_Y="Location_Y";// 地理位置经度
	public static final String WX_TAG_SCALE="scale";// 地图缩放大小
	public static final String WX_TAG_LABEL="label";// 地理位置信息

	public static final String WX_TAG_TITLE="Title";// 消息标题
	public static final String WX_TAG_DESCRIPTION="Description";// 消息描述
	public static final String WX_TAG_URL="Url";// 消息链接
	
	public static final String WX_TAG_EVENT="Event";//事件类型，subscribe(订阅)、unsubscribe(取消订阅)	SCAN
	
	public static final String WX_TAG_EVENTKEY="EventKey";//事件KEY值，qrscene_为前缀，后面为二维码的参数值
	public static final String WX_TAG_TICKET="Ticket";//二维码的ticket，可用来换取二维码图片
	
	public static final String WX_TAG_LATITUDE="Latitude";//地理位置纬度
	public static final String WX_TAG_LONGITUDE="Longitude";//地理位置经度
	public static final String WX_TAG_PRECISION="Precision";//地理位置精度
	//********************************************消息，事件标签名称*********************************************//
}
