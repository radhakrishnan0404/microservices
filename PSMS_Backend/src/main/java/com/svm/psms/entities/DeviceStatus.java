package com.svm.psms.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devicestatus")
public class DeviceStatus{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "imeinumber")
	private String imeinumber;
	
	@Column(name = "timestamp")
	private int timestamp;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "rphasecurrentfinal")
	private Double rphasecurrentfinal;
	
	@Column(name = "yphasecurrentfinal")
	private Double yphasecurrentfinal;
	
	@Column(name = "bphasecurrentfinal")
	private Double bphasecurrentfinal;
	
	@Column(name = "rphasevoltagefinal")
	private Double rphasevoltagefinal;
	
	@Column(name = "yphasevoltagefinal")
	private Double yphasevoltagefinal;
	
	@Column(name = "bphasevoltagefinal")
	private Double bphasevoltagefinal;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImeinumber() {
		return imeinumber;
	}

	public void setImeinumber(String imeinumber) {
		this.imeinumber = imeinumber;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getRphasecurrentfinal() {
		return rphasecurrentfinal;
	}

	public void setRphasecurrentfinal(Double rphasecurrentfinal) {
		this.rphasecurrentfinal = rphasecurrentfinal;
	}

	public Double getYphasecurrentfinal() {
		return yphasecurrentfinal;
	}

	public void setYphasecurrentfinal(Double yphasecurrentfinal) {
		this.yphasecurrentfinal = yphasecurrentfinal;
	}

	public Double getBphasecurrentfinal() {
		return bphasecurrentfinal;
	}

	public void setBphasecurrentfinal(Double bphasecurrentfinal) {
		this.bphasecurrentfinal = bphasecurrentfinal;
	}

	public Double getRphasevoltagefinal() {
		return rphasevoltagefinal;
	}

	public void setRphasevoltagefinal(Double rphasevoltagefinal) {
		this.rphasevoltagefinal = rphasevoltagefinal;
	}

	public Double getYphasevoltagefinal() {
		return yphasevoltagefinal;
	}

	public void setYphasevoltagefinal(Double yphasevoltagefinal) {
		this.yphasevoltagefinal = yphasevoltagefinal;
	}

	public Double getBphasevoltagefinal() {
		return bphasevoltagefinal;
	}

	public void setBphasevoltagefinal(Double bphasevoltagefinal) {
		this.bphasevoltagefinal = bphasevoltagefinal;
	}
	
	 
	
	
}
