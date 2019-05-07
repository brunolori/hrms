package com.bvlsh.hr.ui.models;

import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TreeModel {

	public static final int DEPARTMENT = 1;
	public static final int POSITION = 2;
	

	Integer departmentId;
	Integer positionId;
	String nid;
	String name;
	String position;
	String departmentName;
	int type; //1 dept, 2 position
	
	
	public TreeModel(DepartmentDTO d)
	{
		this.departmentId = d.getId();
		this.departmentName = d.getName();
		this.type = DEPARTMENT;
	}
	
	public TreeModel(DepartmentPositionDTO d)
	{
		this.positionId = d.getId();
		this.departmentId = d.getDepartment().getId();
		if(d.getCurrentEmployee() != null)
		{
			this.nid = d.getCurrentEmployee().getEmployee().getNid();
			this.name = d.getCurrentEmployee().getEmployee().getName()+" "+d.getCurrentEmployee().getEmployee().getSurname();
		}
		this.departmentName = d.getName();
		this.position = d.getPosition().getTag();
		this.type = POSITION;
	}
	
	
	public TreeModel() {
	}

	public String display()
	{
		if(this.type == DEPARTMENT)
		{
			return this.departmentName;
		}
		else
		{
			return "<strong>"+name+"</strong><br/>"+departmentName;
		}
	}
	
	
	
	
	
}
