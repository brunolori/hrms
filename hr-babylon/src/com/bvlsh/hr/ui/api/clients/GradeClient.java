package com.bvlsh.hr.ui.api.clients;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bvlsh.hr.ui.api.security.ApiErrorHandler;
import com.bvlsh.hr.ui.constants.IApiClient;
import com.bvlsh.hr.ui.dto.EmployeeGradeDTO;
import com.bvlsh.hr.ui.forms.GradeForm;
import com.bvlsh.hr.ui.forms.GradeSx;
import com.bvlsh.hr.ui.utils.Util;

@RestController
@RequestMapping("/api/grade")
public class GradeClient {
	
	public EmployeeGradeDTO registerGrade(GradeForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/grade/register";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeGradeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeGradeDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public EmployeeGradeDTO modifyGrade(GradeForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/grade/modifyGrade";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<EmployeeGradeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				EmployeeGradeDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public EmployeeGradeDTO deleteGrade(Integer gradeId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/grade/deleteGrade/"+gradeId;
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
	

	public List<EmployeeGradeDTO> searchGrades(GradeSx sx) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/grade/searchGrades";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(sx, headers);

		ParameterizedTypeReference<List<EmployeeGradeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeGradeDTO>>() {
		};

		ResponseEntity<List<EmployeeGradeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public List<EmployeeGradeDTO> getEmployeeGrades(String nid) {
		final String BASE_URL = IApiClient.SERVER + "/api/grade/getEmployeeGrades/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<EmployeeGradeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeGradeDTO>>() {
		};

		ResponseEntity<List<EmployeeGradeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
}
