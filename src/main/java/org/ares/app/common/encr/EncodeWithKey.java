package org.ares.app.common.encr;

/**
 * have two param
 * data,key=>byte[]
 * @author ly
 */
public interface EncodeWithKey {

	byte[] encode(byte[] data, byte[] key);
	
}
