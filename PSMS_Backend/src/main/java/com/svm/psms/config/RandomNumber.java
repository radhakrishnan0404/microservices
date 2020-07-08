package com.svm.psms.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="randomnumber",catalog="psms",schema="")
public class RandomNumber {
	
	@Id
	@Column
	private String idrandomnumber;
	@Column
	private String emailaddresss;
	
	public String getIdrandomnumber() {
		return idrandomnumber;
	}
	public void setIdrandomnumber(String idrandomnumber) {
		this.idrandomnumber = idrandomnumber;
	}
	public String getEmailaddresss() {
		return emailaddresss;
	}
	public void setEmailaddress(String emailaddresss) {
		this.emailaddresss = emailaddresss;
	}
	

}
