package com.svm.psms.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "loaddistribution")
@IdClass(LoadCompID.class)
public class LoadDistribution implements Serializable {

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
 
	@Column(name = "rphase")
	private double rphase;

	@Column(name = "yphase")
	private double yphase;

	@Column(name = "bphase")
	private double bphase;

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

	public double getRphase() {
		return rphase;
	}

	public void setRphase(double rphase) {
		this.rphase = rphase;
	}

	public double getYphase() {
		return yphase;
	}

	public void setYphase(double yphase) {
		this.yphase = yphase;
	}

	public double getBphase() {
		return bphase;
	}

	public void setBphase(double bphase) {
		this.bphase = bphase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  
	
	
}
