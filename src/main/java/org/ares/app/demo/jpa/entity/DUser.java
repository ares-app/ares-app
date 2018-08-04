package org.ares.app.demo.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the D_USER database table.
 * 
 */
@Entity
@Table(name="D_USER")
@NamedQuery(name="DUser.findAll", query="SELECT d FROM DUser d")
public class DUser implements Serializable {
	private static final long serialVersionUID = 1L;

	

	public DUser() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "DUser [userid=" + userid + ", username=" + username +", email=" + email + "]";
	}
	
	@Id private String userid;
	private String username;
	private String email;
	private String password;
	
}