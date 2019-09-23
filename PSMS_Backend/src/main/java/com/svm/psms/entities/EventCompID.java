package com.svm.psms.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
 
@Embeddable
public class EventCompID  implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String imeinumber;
    private int timestamp;
    
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
     
    
    
}

