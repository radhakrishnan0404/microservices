package com.svm.psms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logindetail",catalog="psms",schema="")
public class LoginDetail {
	
	
	@Id
	@Column(name="Session_id")
	private String Session_id;
	
	@Column(name="Log_in")
	private Date Log_in;
	
	@Column(name="Log_out")
	private Date Log_out;
	
	@Column(name="loginID")
	private String loginID;
	
	@Column(name="status")
	private String status;
	
	@Column(name="ipaddress")
	private String ipaddress;
	
	@Column(name="UserAgent")
	private String UserAgent;
	
	@Column(name="UserAgent_version")
	private String UserAgent_version;

	public String getSession_id() {
		return Session_id;
	}

	public void setSession_id(String session_id) {
		this.Session_id = session_id;
	}

	public Date getLog_in() {
		return Log_in;
	}

	public void setLog_in(Date log_in) {
		Log_in = log_in;
	}

	public Date getLog_out() {
		return Log_out;
	}

	public void setLog_out(Date log_out) {
		Log_out = log_out;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getUserAgent() {
		return UserAgent;
	}

	public void setUserAgent(String userAgent) {
		UserAgent = userAgent;
	}

	public String getUserAgent_version() {
		return UserAgent_version;
	}

	public void setUserAgent_version(String userAgent_version) {
		UserAgent_version = userAgent_version;
	}
	
	
	
	
	

}
