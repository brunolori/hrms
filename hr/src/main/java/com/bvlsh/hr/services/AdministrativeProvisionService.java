package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.AdministrativeProvisionDAO;
import com.bvlsh.hr.entities.AdministrativeProvision;
import com.bvlsh.hr.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.forms.AdministrativeProvisionSx;

@Service
public class AdministrativeProvisionService {

	@Autowired
	AdministrativeProvisionDAO provisionDAO;

	public List<AdministrativeProvision> searchProvisions(AdministrativeProvisionSx sx, String uname) {
		return provisionDAO.searchProvisions(sx);
	}

	public AdministrativeProvision registerProvision(AdministrativeProvisionForm form, String uname) {

		return null;
	}

	public AdministrativeProvision modifyProvision(AdministrativeProvisionForm form, String uname) {

		return null;
	}

	public void deleteProvision(Integer provisionId, String uname) {

	}

}
