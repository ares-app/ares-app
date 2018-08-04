package org.ares.app.common.encr.datakey;

import static org.ares.app.common.util.Util.bytesToHex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.ares.app.common.encr.EncodeWithKey;

public abstract class BaseDataKeySign implements EncodeWithKey {

	public byte[] encode(byte[] data, byte[] key) {
		byte[] r=null;
		try{
			SecretKeySpec signingKey = new SecretKeySpec(key, encrmode);  
			Mac mac = Mac.getInstance(encrmode);  
			mac.init(signingKey);  
			r = mac.doFinal(data);  
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	byte[] encode(byte[] data, String secretkey) {
		return encode(data,secretkey.getBytes());
	}
	
	String convHexHash(byte[] datas,String secretkey){
		if(datas==null)
			return "algorithm generate error!";
		return bytesToHex(encode(datas,secretkey));
	}

	public void setEncrmode(String encrmode) {
		this.encrmode = encrmode;
	}

	private String encrmode="HmacSHA1";//default value
	
}
