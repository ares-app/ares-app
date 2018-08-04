/**
 * Biz Logic Exception
 * @author ly
 */
package org.ares.app.common.exception;

import static org.ares.app.common.conf.Params.*;

@SuppressWarnings("serial")
public class BizLogicException extends RuntimeException {

	public BizLogicException() {
		super();
	}

	public BizLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizLogicException(String message) {
		super(message);
	}

	public BizLogicException(Throwable cause) {
		super(cause);
	}
	
	public BizLogicException(int retCode,String message,Throwable cause) {
		super(message,cause);
		this.retCode=retCode;
	}
	
	public BizLogicException(int retCode,String message) {
		super(message);
		this.retCode=retCode;
	}
	
	public int getRetCode() {
		return retCode;
	}

	private int retCode=RET_BS_PROCESS_ERROR;
}
