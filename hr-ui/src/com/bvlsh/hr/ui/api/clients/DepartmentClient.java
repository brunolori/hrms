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
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.forms.DepartmentForm;
import com.bvlsh.hr.ui.forms.DepartmentPositionForm;
import com.bvlsh.hr.ui.utils.Util;



public class DepartmentClient {

	public DepartmentDTO registerDepartment(DepartmentForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/registerDepartment";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	public List<DepartmentPositionDTO> getDepartmentPositions(Integer deptId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getDepartmentPositions/"+deptId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentPositionDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentPositionDTO>>() {};
		
		ResponseEntity<List<DepartmentPositionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<DepartmentDTO> getChildDepartments(Integer deptId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getChildDepartments/"+deptId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentDTO>>() {};
		
		ResponseEntity<List<DepartmentDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public DepartmentDTO getRootDepartment()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getRootDepartment";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	
	
	
	public DepartmentPositionDTO registerDepartmentPosition(DepartmentPositionForm form) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/registerDepartmentPosition";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<DepartmentPositionDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, DepartmentPositionDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}



	public List<DepartmentDTO> getDepartments() {
		final String BASE_URL = IApiClient.SERVER+"/api/department/list";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentDTO>>() {};
		
		ResponseEntity<List<DepartmentDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}



	public DepartmentDTO getDepartmentById(Integer departmentId) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/"+departmentId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}



	public DepartmentDTO modifyDepartment(DepartmentForm form) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/modifyDepartment";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}



	public DepartmentPositionDTO modifyDepartmentPosition(DepartmentPositionForm form) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/modifyDepartmentPosition";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<DepartmentPositionDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, DepartmentPositionDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}



	public void deleteDepartmentPosition(Integer id) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/deleteDepartmentPosition/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
	    restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Void.class);
	}

	
	
}
