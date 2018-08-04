package org.ares.app.common.oapi.verify;

import static org.ares.app.common.conf.Params.JSON_CALLBACK;
import static org.ares.app.common.conf.Params.OAPI_SIGN;
import static org.ares.app.common.util.Util.bytesToHex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class VerifyComponent {

	public String ident(Map<String,String[]> params,String secretkey) {
		String vals=getValuesAsStrByAsc(params);
		String key=secretkey;
		String comp_sign=buildMD((vals+key).getBytes());
		return comp_sign;
	}
	
	String getValuesAsStrByAsc(Map<String, String[]> params) {
		StringBuilder r = new StringBuilder();
		TreeMap<String, String[]> tree = new TreeMap<String, String[]>();
		for (String k : params.keySet()) {
			if (!notVerify(k) && !k.equals(params.get(JSON_CALLBACK) != null ? params.get(JSON_CALLBACK)[0] : null))
				tree.put(k, params.get(k));
		}
		for (String k : tree.keySet()) {
			String[] pa = tree.get(k);
			for (String s : pa)
				r.append(s);
		}
		return r + "";
	}

	boolean notVerify(String attr) {
		boolean r = false;
		for (String nv : getNotVerifyAttrs()) {
			if (nv.equals(attr)) {
				r = true;
				break;
			}
		}
		return r;
	}

	byte[] encode(byte[] datas) {
		byte[] r=null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(encrmode);
			r= md.digest(datas);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	String buildMD(byte[] datas){
		if(datas==null)
			return "md5 generate error!";
		return bytesToHex(encode(datas));
	}
	
	public String[] getNotVerifyAttrs() {
		return notVerifyAttrs;
	}

	public void setNotVerifyAttrs(String[] notVerifyAttrs) {
		this.notVerifyAttrs = notVerifyAttrs;
	}
	
	public String getEncrmode() {
		return encrmode;
	}

	public void setEncrmode(String encrmode) {
		this.encrmode = encrmode;
	}

	//default value
	private String[] notVerifyAttrs=new String[] { OAPI_SIGN, JSON_CALLBACK };
	private String encrmode="MD5";
}
