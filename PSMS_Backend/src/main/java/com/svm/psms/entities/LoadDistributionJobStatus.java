package com.svm.psms.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "loaddistributionjobstatus")
public class LoadDistributionJobStatus implements Serializable {

	private static final long serialVersionUID = 1L;
 
	@Id
	@Column(name = "imeinumber")
	private String imeinumber;
	
	@Column(name = "lasttimestamp")
	private int lasttimestamp;

	public String getImeinumber() {
		return imeinumber;
	}

	public void setImeinumber(String imeinumber) {
		this.imeinumber = imeinumber;
	}

	public int getLasttimestamp() {
		return lasttimestamp;
	}

	public void setLasttimestamp(int lasttimestamp) {
		this.lasttimestamp = lasttimestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
  
	
}
