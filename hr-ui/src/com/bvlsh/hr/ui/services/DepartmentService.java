package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.DepartmentClient;
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.forms.DepartmentForm;
import com.bvlsh.hr.ui.forms.DepartmentPositionForm;

public class DepartmentService {

	public DepartmentDTO getRootDepartment() {
		return new DepartmentClient().getRootDepartment();
	}

	public List<DepartmentDTO> getChildDepartments(Integer id) {
		return new DepartmentClient().getChildDepartments(id);
	}

	public DepartmentDTO registerDepartment(DepartmentForm form) {
		return new DepartmentClient().registerDepartment(form);
	}

	public DepartmentPositionDTO registerDepartmentPosition(DepartmentPositionForm form) {
		return new DepartmentClient().registerDepartmentPosition(form);
	}

	public List<DepartmentPositionDTO> getDepartmentPositions(Integer deptId) {
		return new DepartmentClient().getDepartmentPositions(deptId);
	}

	public DepartmentPositionDTO getDepartmentSinglePosition(Integer id) {
		List<DepartmentPositionDTO> list = getDepartmentPositions(id);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	

}
