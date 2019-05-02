package com.bvlsh.hr.ui.api.clients;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bvlsh.hr.ui.api.security.ApiErrorHandler;
import com.bvlsh.hr.ui.constants.IApiClient;
import com.bvlsh.hr.ui.dto.EmployeeForeignLanguageDTO;
import com.bvlsh.hr.ui.forms.ForeignLanguageForm;
import com.bvlsh.hr.ui.forms.ForeignLanguageSx;
import com.bvlsh.hr.ui.utils.Util;

public class ForeignLanguageClient
{
	
	public EmployeeForeignLanguageDTO registerForeignLanguage(ForeignLanguageForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/foreignLanguage/register";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeForeignLanguageDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeForeignLanguageDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}


	
	public EmployeeForeignLanguageDTO modifyForeignLanguage(ForeignLanguageForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/foreignLanguage/modifyForeignLanguage";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeForeignLanguageDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeForeignLanguageDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	
	public EmployeeForeignLanguageDTO deleteForeignLanguage(Integer foreignLanguageId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/foreignLanguage/"+foreignLanguageId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
	    restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Void.class);
		
		return null;
	}
	
	public List<EmployeeForeignLanguageDTO> searchForeignLanguages(ForeignLanguageSx sx) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/foreignLanguage/search";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(sx, headers);

		ParameterizedTypeReference<List<EmployeeForeignLanguageDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeForeignLanguageDTO>>() {
		};

		ResponseEntity<List<EmployeeForeignLanguageDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	

}
