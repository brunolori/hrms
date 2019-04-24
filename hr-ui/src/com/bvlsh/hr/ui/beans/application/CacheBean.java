package com.bvlsh.hr.ui.beans.application;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.bvlsh.hr.ui.dto.PositionDTO;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;


@ManagedBean
@ApplicationScoped
@Getter @Setter
public class CacheBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	List<PositionDTO> positions;
	
	
	
	@PostConstruct
	public void load()
	{
		
		
	}
	
	
	
	
	
	
	
	/*
	public List<MunicipalityDTO> loadMunicipalities(Integer regionId, boolean fill)
	{
		if(regionId == null )
		{
			return fill?this.municipalities:null;
		}
		
		List<MunicipalityDTO> list = new ArrayList<>();
		for(MunicipalityDTO m : municipalities)
		{
			if(m.getRegion().getId() == regionId)
			{
				list.add(m);
			}
		}
		
		return list;
		
	}
		*/
	

}	
