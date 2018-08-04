package org.ares.app.sys.wx.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.ares.app.common.xml.adapter.CDataAdapter;

@XmlRootElement(name = "Image")
public class WxImage{
	
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement(name = "MediaId")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	private String mediaId;
}