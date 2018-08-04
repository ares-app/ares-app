package org.ares.app.common.jwt;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Component;

import org.ares.app.common.exception.AppSysException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author ly
 * 依据使用环境，只能做成工具类
 * 
  	 Key key = new AesKey(ByteUtil.randomBytes(16));
	 JsonWebEncryption jwe = new JsonWebEncryption();
	 jwe.setPayload("Hello World!");
	 jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
	 jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
	 jwe.setKey(key);
	 String serializedJwe = jwe.getCompactSerialization();
	 System.out.println("Serialized Encrypted JWE: " + serializedJwe);
	 jwe = new JsonWebEncryption();
	 jwe.setKey(key);
	 jwe.setCompactSerialization(serializedJwe);
	 System.out.println("Payload: " + jwe.getPayload());
 *
 */
@Component
public class AesClaims implements Claims {

	
	@Override
	public String encodeJwt(JwtMessage message) {
		String json,token=null;
		try {
			if(message==null)
				throw new AppSysException("jwt message is null");
            json= om.writeValueAsString(message);
            token=buildTokenFromJson(json);
        } catch (Exception e) {
            throw new AppSysException(e.getMessage());
        }
        return token;
	}
	
	@Override
	public String resultJson(JwtMessage message) {
		String jwt=encodeJwt(message);
		Map<String,String> r=new HashMap<String,String>();
		r.put("jwt", jwt);
		String json=null;
		try {
			json=om.writeValueAsString(r);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@Override
	public String resultJson(List<JwtMessage> messages) {
		int ind=0;
		Map<String,String> r=new HashMap<String,String>();
		for(JwtMessage msg:messages){
			String jwt=encodeJwt(msg);
			r.put("jwt"+(++ind), jwt);
		}
		String json=null;
		try {
			json=om.writeValueAsString(r);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	private String buildTokenFromJson(String json) throws JoseException {
		build_jwe();
		jwe.setPayload(json);
		return jwe.getCompactSerialization();
	}
	
	private void build_jwe(){
		jwe = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(key);
	}

	@Override
	public JwtMessage decodeJwt(String jwt) {
		if(jwt==null)
			throw new AppSysException("token_source is null");
		JwtMessage r=null;
		try {
			build_jwe();
			jwe.setCompactSerialization(jwt);
			String json=jwe.getPayload();
			r=om.readValue(json, JwtMessage.class);
			r.upExpire();
		} catch (Exception e) {
			throw new AppSysException("tranform string to jwtmessage error");
		}
		return r;
	}

	@Override
	public void setKey(Key key) {
		this.key=key;
		jwe.setKey(key);
	}

    private JsonWebEncryption jwe;
    private Key key=new AesKey("ares-app-pass".getBytes());
    private static ObjectMapper om = new ObjectMapper();
    
}
