package com.bvlsh.hr.ui.beans.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.bvlsh.hr.ui.dto.RoleDTO;
import com.bvlsh.hr.ui.dto.UserDTO;
import com.bvlsh.hr.ui.forms.UserForm;
import com.bvlsh.hr.ui.services.HelperService;
import com.bvlsh.hr.ui.services.UserService;
import com.bvlsh.hr.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;


@ManagedBean
@ViewScoped
@Getter @Setter
public class AdUserBean implements Serializable {

	UserForm form;
	List<UserDTO> users;
	UserDTO selectedUser;
	List<RoleDTO> roles;
	
	@PostConstruct
	public void load() {
		init();
		this.roles = new HelperService().loadRoles();
	}

	public void init() {

		this.form = new UserForm();
		this.users = new UserService().loadUsers();
		this.selectedUser = null;
	}

	public void clear() {
		init();
	}

	public void onUserSelect() {
		this.form.setRoleCode(this.selectedUser.getRole().getCode());
		this.form.setUsername(this.selectedUser.getUsername());
		this.form.setSecret(this.selectedUser.getSecret());
		this.form.setRootDepartmentId(this.selectedUser.getRootDepartment().getId());
	}

	public void register() {
		
		try {
    		new UserService().registerUser(form);
    		//this.departmentPositions = new DepartmentService().getDepartmentPositions(this.selectedDepartment.getId());
    		Messages.throwFacesMessage("Përdoruesi u rregjistrua me sukses!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void modify() {
		try {
    		new UserService().modifyUser(form);
    		//this.departmentPositions = new DepartmentService().getDepartmentPositions(this.selectedDepartment.getId());
    		Messages.throwFacesMessage("Përdoruesi u rregjistrua me sukses!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
	}
	
	public void delete() {}

}
