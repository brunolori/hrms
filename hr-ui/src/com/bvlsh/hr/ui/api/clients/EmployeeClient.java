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
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.forms.EmployeeSx;
import com.bvlsh.hr.ui.utils.Util;

public class EmployeeClient {

	public EmployeeDTO registerEmployee(EmployeeForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/employee/register";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public EmployeeDTO changeEmployeePosition(EmployeeForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/employee/changeEmployeePosition";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public EmployeeDTO updateEmployeeGeneralities(EmployeeForm form) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/employee/updateEmployeeGeneralities";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public List<EmployeeDTO> searchEmployee(EmployeeSx sx) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/employee/searchEmployee";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(sx, headers);

		ParameterizedTypeReference<List<EmployeeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeDTO>>() {
		};

		ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public List<EmployeeDTO> getEmployeeHistory(String nid) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/employee/getEmployeeHistory" + nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<EmployeeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeDTO>>() {
		};

		ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public EmployeeDTO getEmployeeByNid(String nid)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/employee/getEmployeeByNid"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<EmployeeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, EmployeeDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

}
