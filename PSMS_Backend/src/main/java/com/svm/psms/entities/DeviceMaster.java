package com.svm.psms.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "devicemaster")
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)
public class DeviceMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("imeinumber")
	@Column(name = "imeinumber")
	private String imeinumber;

	 
	@JsonProperty("description")
	@Column(name = "description")
	private String description;
    
	@JsonProperty("simno")
	@Column(name = "simno")
	private String simno;
	
	@JsonProperty("phase")
	@Column(name = "phase")
	private String phase;

	@JsonProperty("mobileno")
	@Column(name = "mobileno")
	private String mobileno;

	@JsonProperty("mailid")
	@Column(name = "mailid")
	private String mailid;

	@JsonProperty("installationtime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "installationtime")
	private Date installationtime;

	@JsonProperty("lastupdationtime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "lastupdationtime")
	private Date lastupdationtime;

	@JsonProperty("smsalert")
	@Column(name = "smsalert")
	private String smsalert;

	@JsonProperty("rphasecurrenthigh")
	@Column(name = "rphasecurrenthigh")
	private Double rphasecurrenthigh;

	@JsonProperty("yphasecurrenthigh")
	@Column(name = "yphasecurrenthigh")
	private Double yphasecurrenthigh;

	@JsonProperty("bphasecurrenthigh")
	@Column(name = "bphasecurrenthigh")
	private Double bphasecurrenthigh;

	@JsonProperty("rphasevoltagelow")
	@Column(name = "rphasevoltagelow")
	private Double rphasevoltagelow;

	@JsonProperty("yphasevoltagetlow")
	@Column(name = "yphasevoltagetlow")
	private Double yphasevoltagetlow;

	@JsonProperty("bphasevoltagelow")
	@Column(name = "bphasevoltagelow")
	private Double bphasevoltagelow;

	@JsonProperty("rphasevoltagehigh")
	@Column(name = "rphasevoltagehigh")
	private Double rphasevoltagehigh;

	@JsonProperty("yphasevoltagethigh")
	@Column(name = "yphasevoltagethigh")
	private Double yphasevoltagethigh;

	@JsonProperty("bphasevoltagehigh")
	@Column(name = "bphasevoltagehigh")
	private Double bphasevoltagehigh;

	public String getImeinumber() {
		return imeinumber;
	}

	public void setImeinumber(String imeinumber) {
		this.imeinumber = imeinumber;
	}
 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSimno() {
		return simno;
	}

	public void setSimno(String simno) {
		this.simno = simno;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public Date getInstallationtime() {
		return installationtime;
	}

	public void setInstallationtime(Date installationtime) {
		this.installationtime = installationtime;
	}

	public Date getLastupdationtime() {
		return lastupdationtime;
	}

	public void setLastupdationtime(Date lastupdationtime) {
		this.lastupdationtime = lastupdationtime;
	}

	public String getSmsalert() {
		return smsalert;
	}

	public void setSmsalert(String smsalert) {
		this.smsalert = smsalert;
	}

	 

	public Double getRphasecurrenthigh() {
		return rphasecurrenthigh;
	}

	public void setRphasecurrenthigh(Double rphasecurrenthigh) {
		this.rphasecurrenthigh = rphasecurrenthigh;
	}

	public Double getYphasecurrenthigh() {
		return yphasecurrenthigh;
	}

	public void setYphasecurrenthigh(Double yphasecurrenthigh) {
		this.yphasecurrenthigh = yphasecurrenthigh;
	}

	public Double getBphasecurrenthigh() {
		return bphasecurrenthigh;
	}

	public void setBphasecurrenthigh(Double bphasecurrenthigh) {
		this.bphasecurrenthigh = bphasecurrenthigh;
	}

	public Double getRphasevoltagelow() {
		return rphasevoltagelow;
	}

	public void setRphasevoltagelow(Double rphasevoltagelow) {
		this.rphasevoltagelow = rphasevoltagelow;
	}

	public Double getYphasevoltagetlow() {
		return yphasevoltagetlow;
	}

	public void setYphasevoltagetlow(Double yphasevoltagetlow) {
		this.yphasevoltagetlow = yphasevoltagetlow;
	}

	public Double getBphasevoltagelow() {
		return bphasevoltagelow;
	}

	public void setBphasevoltagelow(Double bphasevoltagelow) {
		this.bphasevoltagelow = bphasevoltagelow;
	}

	public Double getRphasevoltagehigh() {
		return rphasevoltagehigh;
	}

	public void setRphasevoltagehigh(Double rphasevoltagehigh) {
		this.rphasevoltagehigh = rphasevoltagehigh;
	}

	public Double getYphasevoltagethigh() {
		return yphasevoltagethigh;
	}

	public void setYphasevoltagethigh(Double yphasevoltagethigh) {
		this.yphasevoltagethigh = yphasevoltagethigh;
	}

	public Double getBphasevoltagehigh() {
		return bphasevoltagehigh;
	}

	public void setBphasevoltagehigh(Double bphasevoltagehigh) {
		this.bphasevoltagehigh = bphasevoltagehigh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}
	
	

}
