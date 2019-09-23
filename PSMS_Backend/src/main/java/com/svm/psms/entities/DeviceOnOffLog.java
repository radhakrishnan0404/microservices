package com.svm.psms.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deviceonofflog")
public class DeviceOnOffLog {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "imeinumber")
	private String imeinumber;

	@Column(name = "deviceontime")
	private int deviceontime;

	@Column(name = "deviceofftime")
	private int deviceofftime;

	@Column(name = "runhours")
	private String runhours;

	@Column(name = "alerts")
	private String alerts;

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

	public Integer getDeviceontime() {
		return deviceontime;
	}

	public void setDeviceontime(Integer deviceontime) {
		this.deviceontime = deviceontime;
	}

	public int getDeviceofftime() {
		return deviceofftime;
	}

	public void setDeviceofftime(int deviceofftime) {
		this.deviceofftime = deviceofftime;
	}

	public String getRunhours() {
		return runhours;
	}

	public void setRunhours(String runhours) {
		this.runhours = runhours;
	}

	public String getAlerts() {
		return alerts;
	}

	public void setAlerts(String alerts) {
		this.alerts = alerts;
	}

}
