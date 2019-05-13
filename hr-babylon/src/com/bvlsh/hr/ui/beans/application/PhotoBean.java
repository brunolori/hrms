package com.bvlsh.hr.ui.beans.application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.bvlsh.hr.ui.services.PhotoService;
import com.bvlsh.hr.ui.utils.Messages;
import com.bvlsh.hr.ui.utils.PhotoUtil;
import com.bvlsh.hr.ui.utils.StringUtil;






@ManagedBean
@ApplicationScoped
public class PhotoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	public StreamedContent getPhoto() throws IOException
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
		{
			return new DefaultStreamedContent();
		}
		
		byte[] imgByte = Base64.getMimeDecoder().decode(PhotoUtil.NO_IMAGE);
		
		try {
						
			String nid = context.getExternalContext().getRequestParameterMap().get("nid");
			if(!StringUtil.isValid(nid))
			{
				return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
			}
			

			String base64 = new PhotoService().getPhotoByNid(nid);
			if(StringUtil.isValid(base64))
			{
				imgByte = Base64.getMimeDecoder().decode(base64);
			}
			
			
			return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
			
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
			return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
		}
		
		
		
	}

}
