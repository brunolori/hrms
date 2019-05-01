package com.bvlsh.hr.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GradeForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date startDate;
    private Date endDate;
    private Integer gradeId;
    private String personNid;
	

}
