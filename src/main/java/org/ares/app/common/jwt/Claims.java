package org.ares.app.common.jwt;

import java.security.Key;
import java.util.List;

public interface Claims {

	String resultJson(JwtMessage message);
	String resultJson(List<JwtMessage> messages);
    String encodeJwt(JwtMessage message);
    JwtMessage decodeJwt(String jwt);
    void setKey(Key key);

}
