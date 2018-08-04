package org.ares.app.common.oapi.time;

import java.util.Calendar;

public class DefaultTimeServiceImp implements TimeService {

	@Override
	public long getServerUnixTime() {
		return Calendar.getInstance().getTimeInMillis()/1000;
	}

}
