package org.ares.app.common.encr.data;

import static org.ares.app.common.conf.Params.CHARSET_ENCODING;
import static org.ares.app.common.util.Util.bytesToHex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.ares.app.common.encr.EncodeNotKey;

public abstract class BaseDataSign implements EncodeNotKey {

	public byte[] encode(byte[] data) {
		byte[] r=null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance(encrmode);
			r = md.digest(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	byte[] encode(String source) {
		return encode(source,CHARSET_ENCODING);
	}
	
	byte[] encode(String source,String charset) {
		byte[] r=null;
		try {
			r= encode(source.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	protected String convHexHash(String source){
		if(source==null)
			return "algorithm generate error!";
		return bytesToHex(encode(source));
	}
	
	protected String convHexHash(byte[] datas){
		if(datas==null)
			return "algorithm generate error!";
		return bytesToHex(encode(datas));
	}

	public void setEncrmode(String encrmode) {
		this.encrmode = encrmode;
	}
	
	private String encrmode="SHA-1";//default value
	
}
