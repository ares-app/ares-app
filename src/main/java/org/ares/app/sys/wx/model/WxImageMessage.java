package org.ares.app.sys.wx.model;

import static org.ares.app.sys.wx.conf.WxParams.WX_MSG_XML_ROOT;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = WX_MSG_XML_ROOT)
public class WxImageMessage extends WxBaseMessage {
	
	public WxImage getImage() {
		return image;
	}

	public void setImage(WxImage image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxImageMessage [");
		if (image != null) {
			builder.append("image=");
			builder.append(image);
			builder.append(", ");
		}
		if (super.toString() != null) {
			builder.append("toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}

	private WxImage image;
}

