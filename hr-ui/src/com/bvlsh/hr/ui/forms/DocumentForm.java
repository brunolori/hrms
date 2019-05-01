package com.bvlsh.hr.ui.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DocumentForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String documentName;
    private Date documentDate;
    private String description;
    private String path;
    private String personNid;
    private String data;

}
