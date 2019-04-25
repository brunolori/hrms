package com.bvlsh.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvlsh.hr.dao.AdministrativeProvisionDAO;
import com.bvlsh.hr.entities.AdministrativeProvision;
import com.bvlsh.hr.forms.AdministrationProvisionForm;
import com.bvlsh.hr.forms.AdministrativeProvisionSx;

@Service
public class AdministrativeProvisionService {

	@Autowired
	AdministrativeProvisionDAO provisionDAO;

	public List<AdministrativeProvision> searchProvisions(AdministrativeProvisionSx sx, String uname) {
		return provisionDAO.searchProvisions(sx);
	}

	public AdministrativeProvision registerProvision(AdministrationProvisionForm form, String uname) {

		return null;
	}

	public AdministrativeProvision modifyProvision(AdministrationProvisionForm form, String uname) {

		return null;
	}

	public void deleteProvision(Integer provisionId, String uname) {

	}

}
