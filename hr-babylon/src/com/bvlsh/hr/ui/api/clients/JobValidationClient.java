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
import com.bvlsh.hr.ui.dto.JobValidationDTO;
import com.bvlsh.hr.ui.forms.JobValidationForm;
import com.bvlsh.hr.ui.forms.JobValidationSx;
import com.bvlsh.hr.ui.utils.Util;

public class JobValidationClient {
	
	public JobValidationDTO registerJobValidation(JobValidationForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/jobValidation/register";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<JobValidationDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				JobValidationDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}


	
	public JobValidationDTO modifyJobValidation(JobValidationForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/jobValidation/modifyJobValidation";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<JobValidationDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				JobValidationDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	
	public JobValidationDTO deleteJobValidation(Integer jobValidationId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/jobValidation/deleteJobValidation/"+jobValidationId;
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
	
	public List<JobValidationDTO> searchJobValidations(JobValidationSx sx) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/jobValidation/searchJobValidations";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(sx, headers);

		ParameterizedTypeReference<List<JobValidationDTO>> typeRef = new ParameterizedTypeReference<List<JobValidationDTO>>() {
		};

		ResponseEntity<List<JobValidationDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}



	public List<JobValidationDTO> getEmployeeValidations(String nid) {
		final String BASE_URL = IApiClient.SERVER + "/api/jobValidation/getEmployeeValidations/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<JobValidationDTO>> typeRef = new ParameterizedTypeReference<List<JobValidationDTO>>() {
		};

		ResponseEntity<List<JobValidationDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	
	
}
