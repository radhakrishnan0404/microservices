package com.svm.psms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="contactusmails",catalog="psms",schema="")
public class ContactUsMails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column 
	private int id;
	@Column
	private String maileduser;
	
	@Column
	private String subject;
	
	@Column
	private String message;
	
	@Column
	private String status;
	
	@Column

	private String reason;
	
	@Column

	private Date maileddate;
	
	@Column
	private String usermailid;
	
	@Column
	
	private Date  usermaileddate;
	
	@Column
	private String ccmailids;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaileduser() {
		return maileduser;
	}

	public void setMaileduser(String maileduser) {
		this.maileduser = maileduser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getMaileddate() {
		return maileddate;
	}

	public void setMaileddate(Date maileddate) {
		this.maileddate = maileddate;
	}

	public String getUsermailid() {
		return usermailid;
	}

	public void setUsermailid(String usermailid) {
		this.usermailid = usermailid;
	}

	public Date getUsermaileddate() {
		return usermaileddate;
	}

	public void setUsermaileddate(Date usermaileddate) {
		this.usermaileddate = usermaileddate;
	}

	public String getCcmailids() {
		return ccmailids;
	}

	public void setCcmailids(String ccmailids) {
		this.ccmailids = ccmailids;
	}

	public String getTomailid() {
		return tomailid;
	}

	public void setTomailid(String tomailid) {
		this.tomailid = tomailid;
	}

	public String getBccmail() {
		return bccmail;
	}

	public void setBccmail(String bccmail) {
		this.bccmail = bccmail;
	}

	@Column
	private String tomailid;
	
	@Column
	private String bccmail;
	
	
	

}
