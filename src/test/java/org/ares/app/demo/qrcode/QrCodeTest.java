package org.ares.app.demo.qrcode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ares.app.common.qrcode.DefaultQrCode;
import org.ares.app.common.qrcode.QrCodeGenerator;
import org.junit.Test;

import com.google.zxing.WriterException;

public class QrCodeTest {

	/**
	 * 生成图像
	 */
	//@Test
	public void testEncode() throws WriterException, IOException {
		Map<String, Object> param = new HashMap<>();
		param.put(QrCodeGenerator.QRC_CONTENT, "Hello,World");
		String fileName = "C:/java/tmp/cc.png";
		param.put(QrCodeGenerator.QRC_FILE_PATH, fileName);
		qrc.encode(param);
	}

	/**
	 * 解析图像
	 */
	@Test
	public void testDecode() {
		String fileName = "C:/java/tmp/cc.png";
		String r=qrc.decode(fileName);
		System.out.println(r);
	}

	private QrCodeGenerator qrc = new DefaultQrCode();
}
