package org.ares.app.common.encr.data;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import org.ares.app.common.encr.SignGenerator;

/**
 * for wechat,...
 * SHA-1	setEncrmode(encrmode);
 * @author ly
 *
 */
public class WxchatDictSign extends BaseDataSign implements SignGenerator  {

	/**
	 * now allow don't imp
	 */
	@Override
	public String serverSign(Map<String, Object> params) {
		return null;
	}

	@Override
	public String hashSign(Map<String, Object> params) {
		String token=params.get(WX_TOKEN)+"";
		String timestamp=params.get(WX_TIMESTAMP)+"";
		String nonce=params.get(WX_NONCE)+"";
		return hashSign(token,timestamp,nonce);
	}
	
	/**
	 * ab,1234,3ax=>12343axab
	 * dict order
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	String hashSign(String token,String timestamp,String nonce) {
		String[] ary = new String[] { token, timestamp, nonce };
		Arrays.sort(ary);
		return convHexHash(Stream.of(ary).reduce("",(s,e)->s+e));
	}
	
	public static final String WX_SIGNATURE="signature";//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	public static final String WX_TIMESTAMP="timestamp";
	public static final String WX_NONCE="nonce";//随机数
	public static final String WX_TOKEN="token";//app token
}
