package com.bvlsh.hr.ui.services;

import com.bvlsh.hr.ui.api.clients.NcrClient;
import com.bvlsh.hr.ui.models.PhotoRest;

public class PhotoService {

	public String getPhotoByNid(String nid) {
		
		PhotoRest r = new NcrClient().getPersonPhoto(nid);
		if(r != null) return r.getPhotoBase64();
		
		return null;
	}

}
