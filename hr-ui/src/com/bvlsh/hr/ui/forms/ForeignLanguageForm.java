package com.bvlsh.hr.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForeignLanguageForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String level;
    private String description;
    private Integer foreignLanguageId;
    private String personNid;


}
