package com.bvlsh.hr.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JobEndingReasonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String tag;
    private boolean status;
	
}
