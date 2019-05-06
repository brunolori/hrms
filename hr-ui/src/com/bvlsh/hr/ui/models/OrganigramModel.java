package com.bvlsh.hr.ui.models;

import java.io.Serializable;

import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganigramModel implements Serializable {
	
	
	public static final int DEPARTMENT = 1;
	public static final int POSITION = 2;
	

	Integer departmentId;
	Integer positionId;
	String nid;
	String name;
	String position;
	String departmentName;
	boolean singlePosition;
	boolean expanded;
	String color;
	int type; //1 dept, 2 position
	
	public OrganigramModel(DepartmentDTO d)
	{
		this.departmentId = d.getId();
		this.departmentName = d.getName();
		this.singlePosition = (d.getPositionsNo() != null && d.getPositionsNo() == 1);
		this.expanded = d.isExpanded();
		this.color = d.getColor();
		this.type = DEPARTMENT;
	}
	
	public OrganigramModel(DepartmentPositionDTO d)
	{
		this.positionId = d.getId();
		this.departmentId = d.getDepartment().getId();
		this.singlePosition = (d.getDepartment().getPositionsNo() != null && d.getDepartment().getPositionsNo() == 1);
		if(d.getCurrentEmployee() != null)
		{
			this.nid = d.getCurrentEmployee().getEmployee().getNid();
			this.name = d.getCurrentEmployee().getEmployee().getName()+" "+d.getCurrentEmployee().getEmployee().getSurname();
		}
		this.departmentName = d.getName();
		this.position = d.getPosition().getTag();
		this.expanded = d.getDepartment().isExpanded();
		this.color = d.getDepartment().getColor();
		this.type = POSITION;
	}
	
	
	public OrganigramModel() {
		// TODO Auto-generated constructor stub
	}

	public String display()
	{
		if(this.type == DEPARTMENT)
		{
			return (this.singlePosition?"<span style='color:#ff1300'>Pa përcaktuar</span><br/>":"")+this.departmentName;
		}
		else
		{
			return "<strong>"+name+"</strong><br/>"+departmentName;
		}
	}
	
	
}
