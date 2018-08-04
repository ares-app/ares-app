package org.ares.app.common.qrcode;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.ares.app.common.exception.BizLogicException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class DefaultQrCode implements QrCodeGenerator {

	protected boolean checkParam(Map<String, Object> params){
		boolean r=true;
		if(!params.containsKey(QRC_CONTENT))
			return false;
		if(!( params.containsKey(QRC_TARGET_STREAM)||params.containsKey(QRC_FILE_PATH)))
			return false;
		return r;
	}
	
	@Override
	public void encode(Map<String, Object> params) {
		if(!checkParam(params))
			throw new BizLogicException("param is error!");
		int height=QRC_DEFAULT_HEIGHT_VALUE;
		int width=QRC_DEFAULT_WIDTH_VALUE;
		String format=QRC_DEFAULT_FORMAT_VALUE;
		
		OutputStream out=null;
		String content=params.get(QRC_CONTENT)+"";
		if(params.containsKey(QRC_HEIGHT))
			height=((Integer)params.get(QRC_HEIGHT)).intValue();
		if(params.containsKey(QRC_WIDTH))
			width=((Integer)params.get(QRC_WIDTH)).intValue();
		if(params.containsKey(QRC_FORMAT))
			format=params.get(QRC_FORMAT)+"";
		
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix;
		try {
			if(params.containsKey(QRC_TARGET_STREAM))
				out=(OutputStream)params.get(QRC_TARGET_STREAM);
			else{
				String fileName=params.get(QRC_FILE_PATH)+"";
				out=new FileOutputStream(fileName);
			}
			matrix = mfw.encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
			MatrixToImageWriter.writeToStream(matrix, format, out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizLogicException("qrcode generator is error!");
		}
	}

	@Override
	public String decode(InputStream in) {
		String r=null;
		BufferedImage image=null; 
		try {
			image = ImageIO.read(in);
			 LuminanceSource source = new BufferedImageLuminanceSource(image);  
	            Binarizer binarizer = new HybridBinarizer(source);  
	            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
	            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
	            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
	            Result result = mfr.decode(binaryBitmap, hints);// 对图像进行解码  
	            r=result.getText();
		} catch (Exception e) {
			throw new BizLogicException("process target image failed!");
		} 
		return r;
	}
	
	@Override
	public String decode(String fileName) {
		String r=null;
		try {
			r= decode(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			throw new BizLogicException("Target file not found!");
		}
		return r;
	}
	
	static MultiFormatWriter mfw = new MultiFormatWriter();
	static MultiFormatReader mfr=new MultiFormatReader();

}
