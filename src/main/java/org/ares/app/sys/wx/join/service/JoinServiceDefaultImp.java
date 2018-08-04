package org.ares.app.sys.wx.join.service;

import org.springframework.stereotype.Service;

@Service
public class JoinServiceDefaultImp implements JoinService {

	@Override
	public String getContent(String content) {
		return "["+content+"]";
	}

}
