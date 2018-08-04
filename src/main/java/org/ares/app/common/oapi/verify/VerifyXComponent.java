package org.ares.app.common.oapi.verify;

import static org.ares.app.common.util.Util.bytesToHex;

import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC_SHA1
 * 新式OPENAPI校验
 * 算法：哈希算法 HMAC_SHA1,HMAC_SHA1(consid+IP+timestamp)用secretKey作为密钥，默认小写
 * sign=flag:ATP:accesskey:timestamp:HMAC_SHA1{secretkey}(accesskey+IP+timestamp) 
 * @author ly
 */
public class VerifyXComponent {

	
	public long getServerUnixTime(){
		return Calendar.getInstance().getTimeInMillis()/1000;
	}
	
	public String ident(String accesskey,String ip,Long reqTime,String secretkey) {
		String r=null;
		long serverTime=getServerUnixTime();
		long len=serverTime-reqTime;
		if(!(len>0 && len<maxsecond))
			return null;
		r=buildMD((accesskey+ip+reqTime).getBytes(),secretkey);//********************
		return r;
	}
	
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
	
	public byte[] encode(byte[] data, String secretkey) {
		return encode(data,secretkey.getBytes());
	}
	
	String buildMD(byte[] datas,String secretkey){
		if(datas==null)
			return "md5 generate error!";
		return bytesToHex(encode(datas,secretkey));
	}
	
	
	public String getEncrmode() {
		return encrmode;
	}

	public void setEncrmode(String encrmode) {
		this.encrmode = encrmode;
	}

	public void setMaxsecond(int maxsecond) {
		this.maxsecond = maxsecond;
	}
	
	/*public void setPrefix(String prefix) {
		this.prefix = prefix;
	}*/

	//default value
	private String encrmode="HmacSHA1";
	private int maxsecond=300; //second scope default 5 minute
	//private String prefix="NEU:";
	
}
