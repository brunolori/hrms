package com.bvlsh.hr.ui.services;

import java.util.List;
import com.bvlsh.hr.ui.api.clients.AdministrativeProvisionClient;
import com.bvlsh.hr.ui.dto.AdministrativeProvisionDTO;
import com.bvlsh.hr.ui.forms.AdministrativeProvisionForm;
import com.bvlsh.hr.ui.forms.AdministrativeProvisionSx;

public class AdministrativeProvisionService {

	public AdministrativeProvisionDTO registerAdministrativeProvision(AdministrativeProvisionForm form) {
		return new AdministrativeProvisionClient().registerAdministrativeProvision(form);
	}

	public AdministrativeProvisionDTO modifyProvision(AdministrativeProvisionForm form) {
		return new AdministrativeProvisionClient().modifyProvision(form);
	}

	public AdministrativeProvisionDTO deleteProvision(Integer provisionId) {
		return new AdministrativeProvisionClient().deleteProvision(provisionId);
	}

	public List<AdministrativeProvisionDTO> searchProvisions(AdministrativeProvisionSx sx) {
		return new AdministrativeProvisionClient().searchProvisions(sx);
	}

}
