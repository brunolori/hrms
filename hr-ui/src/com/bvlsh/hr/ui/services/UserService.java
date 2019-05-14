package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.UserClient;
import com.bvlsh.hr.ui.dto.UserDTO;
import com.bvlsh.hr.ui.forms.PasswordForm;
import com.bvlsh.hr.ui.forms.UserForm;
import com.bvlsh.hr.ui.models.Principal;
import com.bvlsh.hr.ui.models.UserToken;

public class UserService {

	public UserToken login(Principal principal) {
		return new UserClient().login(principal);
	}

	public void changePassword(PasswordForm form) {
		new UserClient().changePassword(form);

	}

	public List<UserDTO> loadUsers() {
		return new UserClient().loadUsers();
	}

	public UserDTO registerUser(UserForm form) {
		return new UserClient().registerUser(form);
	}

	public UserDTO modifyUser(UserForm form) {
		return new UserClient().modifyUser(form);
	}

}
