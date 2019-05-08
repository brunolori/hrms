package com.bvlsh.hr.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class KeyValue implements Serializable {

	private static final long serialVersionUID = 1L;
	

	String key;
	Object value;
	
	public KeyValue() {}
	

	public KeyValue(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyValue(int key, Object value) {
		this.key = key+"";
		this.value = value;
	}
	
	public KeyValue(Date key, Object value) {
		if(key == null) this.key="10/05";
		else this.key = new SimpleDateFormat("dd/MM").format(key);
		this.value = value;
	}
	
	
	
	
	
}
