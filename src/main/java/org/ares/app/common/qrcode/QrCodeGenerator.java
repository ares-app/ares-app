package org.ares.app.common.qrcode;

import java.io.InputStream;
import java.util.Map;

public interface QrCodeGenerator {

	/**
	 * key in {QRC_HEIGHT,QRC_WIDTH,qrc_content,qrc_format}
	 * @param params
	 */
	void encode(Map<String,Object> params);
	
	String decode(InputStream in);
	
	String decode(String fileName);
	
	String QRC_HEIGHT="qrc_height";
	String QRC_WIDTH="qrc_width";
	String QRC_CONTENT="qrc_content";
	String QRC_FILE_PATH="qrc_fpath";
	String QRC_FORMAT="qrc_format";
	String QRC_TARGET_STREAM="qrc_target";
	
	String QRC_DEFAULT_FORMAT_VALUE="png";
	int QRC_DEFAULT_HEIGHT_VALUE= 400;
	int QRC_DEFAULT_WIDTH_VALUE= 400;
}
