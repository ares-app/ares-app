package org.ares.app.common.jwt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class JwtMessage implements Serializable {

    public JwtMessage(){
    }

    public JwtMessage(boolean first){
        this.first=first;
        upExpire();
    }

    public String getIssuser() {
        return issuser;
    }

    public void setIssuser(String issuser) {
        this.issuser = issuser;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public Long getValidstart() {
        return validstart;
    }

    public void setValidstart(Long validstart) {
        this.validstart = validstart;
    }
    
    public void upExpire(){
		int day = validaysize[0];
		if (!first)
			day = validaysize[1];
		expire = validstart + 1l * day * 24 * 60 * 60 * 1000;
    }

    public String getSinglemsg() {
        return singlemsg;
    }

    public void setSinglemsg(String singlemsg) {
        this.singlemsg = singlemsg;
    }

    public List<String> getMultimsg() {
        return multimsg;
    }

    public void setMultimsg(List<String> multimsg) {
        this.multimsg = multimsg;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    @Override
	public String toString() {
		return "jwt_message_body : {issuser=" + issuser + ", audience=" + audience
				+ ", expire=" + sdf.format(expire) + ", validstart=" + sdf.format(new Date(validstart))
				+ ", singlemsg=" + singlemsg + ", multimsg=" + multimsg + "}";
	}

	private String issuser="xapp";
    private String audience;
    private Long expire=new Long(0);//token is end
    private Long validstart=new Long(0);//token is valid from
    private String singlemsg;//a
    private List<String> multimsg;//[a,b,c]
    private boolean first;//token is first or second
    private static int[] validaysize={7,30};
    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
}
