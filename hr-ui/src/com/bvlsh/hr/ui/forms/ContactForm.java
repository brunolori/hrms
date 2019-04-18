package com.bvlsh.hr.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String value;
    private Integer contactTypeId;
    private String personNid;

}
