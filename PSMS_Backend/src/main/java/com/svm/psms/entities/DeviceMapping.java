package com.svm.psms.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devicemapping")
public class DeviceMapping{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "imeinumber")
	private String imeinumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImeinumber() {
		return imeinumber;
	}

	public void setImeinumber(String imeinumber) {
		this.imeinumber = imeinumber;
	}
}
