package com.svm.psms.entities;

import javax.persistence.*;
import java.io.Serializable;
 

@Entity
@Table(name = "eventdata")
@IdClass(EventCompID.class)
public class EventData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "imeinumber")
	private String imeinumber;
	@Id
	@Column(name = "timestamp")
	private int timestamp;

	@Column(name = "status")
	private String status;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "speed")
	private double speed;

	@Column(name = "direction")
	private double direction;

	@Column(name = "tamper")
	private double tamper;

	@Column(name = "extsupply")
	private double extsupply;

	@Column(name = "intsupply")
	private double intsupply;

	@Column(name = "gpssignal")
	private int gpssignal;

	@Column(name = "gsmsignal")
	private int gsmsignal;

	@Column(name = "ignitionvoltage")
	private String ignitionvoltage;

	@Column(name = "odometer")
	private double odometer;

	@Column(name = "fuelsensorvolt")
	private double fuelsensorvolt;

	@Column(name = "fuellevel")
	private double fuellevel;

	@Column(name = "rphasecurrent")
	private double rphasecurrent;

	@Column(name = "yphasecurrent")
	private double yphasecurrent;

	@Column(name = "bphasecurrent")
	private double bphasecurrent;

	@Column(name = "rphasevoltage")
	private double rphasevoltage;

	@Column(name = "yphasevoltage")
	private double yphasevoltage;

	@Column(name = "bphasevoltage")
	private double bphasevoltage;
	
	
	
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
	

	@Column(name = "wharehouseontime")
	private int wharehouseontime;

	@Column(name = "wharehouseofftime")
	private int wharehouseofftime;

	@Column(name = "electricalcontractorstatus")
	private int electricalcontractorstatus;

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getTamper() {
		return tamper;
	}

	public void setTamper(double tamper) {
		this.tamper = tamper;
	}

	public double getExtsupply() {
		return extsupply;
	}

	public void setExtsupply(double extsupply) {
		this.extsupply = extsupply;
	}

	public double getIntsupply() {
		return intsupply;
	}

	public void setIntsupply(double intsupply) {
		this.intsupply = intsupply;
	}

	public int getGpssignal() {
		return gpssignal;
	}

	public void setGpssignal(int gpssignal) {
		this.gpssignal = gpssignal;
	}

	public int getGsmsignal() {
		return gsmsignal;
	}

	public void setGsmsignal(int gsmsignal) {
		this.gsmsignal = gsmsignal;
	}

	public String getIgnitionvoltage() {
		return ignitionvoltage;
	}

	public void setIgnitionvoltage(String ignitionvoltage) {
		this.ignitionvoltage = ignitionvoltage;
	}

	public double getOdometer() {
		return odometer;
	}

	public void setOdometer(double odometer) {
		this.odometer = odometer;
	}

	public double getFuelsensorvolt() {
		return fuelsensorvolt;
	}

	public void setFuelsensorvolt(double fuelsensorvolt) {
		this.fuelsensorvolt = fuelsensorvolt;
	}

	public double getFuellevel() {
		return fuellevel;
	}

	public void setFuellevel(double fuellevel) {
		this.fuellevel = fuellevel;
	}

	public double getRphasecurrent() {
		return rphasecurrent;
	}

	public void setRphasecurrent(double rphasecurrent) {
		this.rphasecurrent = rphasecurrent;
	}

	public double getYphasecurrent() {
		return yphasecurrent;
	}

	public void setYphasecurrent(double yphasecurrent) {
		this.yphasecurrent = yphasecurrent;
	}

	public double getBphasecurrent() {
		return bphasecurrent;
	}

	public void setBphasecurrent(double bphasecurrent) {
		this.bphasecurrent = bphasecurrent;
	}

	public double getRphasevoltage() {
		return rphasevoltage;
	}

	public void setRphasevoltage(double rphasevoltage) {
		this.rphasevoltage = rphasevoltage;
	}

	public double getYphasevoltage() {
		return yphasevoltage;
	}

	public void setYphasevoltage(double yphasevoltage) {
		this.yphasevoltage = yphasevoltage;
	}

	public double getBphasevoltage() {
		return bphasevoltage;
	}

	public void setBphasevoltage(double bphasevoltage) {
		this.bphasevoltage = bphasevoltage;
	}

	public int getWharehouseontime() {
		return wharehouseontime;
	}

	public void setWharehouseontime(int wharehouseontime) {
		this.wharehouseontime = wharehouseontime;
	}

	public int getWharehouseofftime() {
		return wharehouseofftime;
	}

	public void setWharehouseofftime(int wharehouseofftime) {
		this.wharehouseofftime = wharehouseofftime;
	}

	public int getElectricalcontractorstatus() {
		return electricalcontractorstatus;
	}

	public void setElectricalcontractorstatus(int electricalcontractorstatus) {
		this.electricalcontractorstatus = electricalcontractorstatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
