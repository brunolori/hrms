package com.bvlsh.hr.ui.services;

import com.bvlsh.hr.ui.api.clients.UserClient;
import com.bvlsh.hr.ui.forms.PasswordForm;
import com.bvlsh.hr.ui.models.Principal;
import com.bvlsh.hr.ui.models.UserToken;

public class UserService {

	public UserToken login(Principal principal) {
		return new UserClient().login(principal);
	}

	public void changePassword(PasswordForm form) {
		new UserClient().changePassword(form);
		
	}

}
