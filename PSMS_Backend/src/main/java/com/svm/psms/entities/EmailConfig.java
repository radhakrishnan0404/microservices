package com.svm.psms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emailconfig",catalog="psms",schema="")
public class EmailConfig {
	
@Id 
@Column
private String attribute;
public String getAttribute() {
	return attribute;
}
public void setAttribute(String attribute) {
	this.attribute = attribute;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
@Column
private String value;
}
