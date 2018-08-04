package org.ares.app.common.exception;

import static org.ares.app.common.conf.Params.RET_BS_PROCESS_ERROR;

/**
 * App Sys Exception
 * @author ly
 */
@SuppressWarnings("serial")
public class AppSysException extends RuntimeException {

	public AppSysException() {
		super();
	}

	public AppSysException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppSysException(String message) {
		super(message);
	}

	public AppSysException(Throwable cause) {
		super(cause);
	}
	
	public AppSysException(int retCode,String message,Throwable cause) {
		super(message,cause);
		this.retCode=retCode;
	}
	
	public AppSysException(int retCode,String message) {
		super(message);
		this.retCode=retCode;
	}
	
	public int getRetCode() {
		return retCode;
	}

	private int retCode=RET_BS_PROCESS_ERROR;
	
}
