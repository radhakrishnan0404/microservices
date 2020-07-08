package com.svm.psms.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "devicealert")
@IdClass(LoadCompID.class)
public class DeviceAlert implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Id
	@Column(name = "timestamp")
	private int timestamp;

	@Id
	@Column(name = "imeinumber")
	private String imeinumber;

	@Column(name = "alert")
	private String alert;

	@Column(name = "rphasecurrentfinal")
	private double rphasecurrentfinal;

	@Column(name = "yphasecurrentfinal")
	private double yphasecurrentfinal;

	@Column(name = "bphasecurrentfinal")
	private double bphasecurrentfinal;

	@Column(name = "rphasevoltagefinal")
	private double rphasevoltagefinal;

	@Column(name = "yphasevoltagefinal")
	private double yphasevoltagefinal;

	@Column(name = "bphasevoltagefinal")
	private double bphasevoltagefinal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getImeinumber() {
		return imeinumber;
	}

	public void setImeinumber(String imeinumber) {
		this.imeinumber = imeinumber;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public double getRphasecurrentfinal() {
		return rphasecurrentfinal;
	}

	public void setRphasecurrentfinal(double rphasecurrentfinal) {
		this.rphasecurrentfinal = rphasecurrentfinal;
	}

	public double getYphasecurrentfinal() {
		return yphasecurrentfinal;
	}

	public void setYphasecurrentfinal(double yphasecurrentfinal) {
		this.yphasecurrentfinal = yphasecurrentfinal;
	}

	public double getBphasecurrentfinal() {
		return bphasecurrentfinal;
	}

	public void setBphasecurrentfinal(double bphasecurrentfinal) {
		this.bphasecurrentfinal = bphasecurrentfinal;
	}

	public double getRphasevoltagefinal() {
		return rphasevoltagefinal;
	}

	public void setRphasevoltagefinal(double rphasevoltagefinal) {
		this.rphasevoltagefinal = rphasevoltagefinal;
	}

	public double getYphasevoltagefinal() {
		return yphasevoltagefinal;
	}

	public void setYphasevoltagefinal(double yphasevoltagefinal) {
		this.yphasevoltagefinal = yphasevoltagefinal;
	}

	public double getBphasevoltagefinal() {
		return bphasevoltagefinal;
	}

	public void setBphasevoltagefinal(double bphasevoltagefinal) {
		this.bphasevoltagefinal = bphasevoltagefinal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
