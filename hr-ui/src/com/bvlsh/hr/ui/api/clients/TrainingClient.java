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
import com.bvlsh.hr.ui.dto.TrainingDTO;
import com.bvlsh.hr.ui.forms.TrainingForm;
import com.bvlsh.hr.ui.forms.TrainingSx;
import com.bvlsh.hr.ui.utils.Util;

@RestController
@RequestMapping("/api/training")
public class TrainingClient {
	
	public TrainingDTO registerTraining(TrainingForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/training/register";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<TrainingDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				TrainingDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public TrainingDTO modifyTraining(TrainingForm form)
	{
		final String BASE_URL = IApiClient.SERVER + "/api/training/modify";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(form, headers);

		ResponseEntity<TrainingDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				TrainingDTO.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	
	public TrainingDTO deleteTraining(Integer trainingId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/training/delete/"+trainingId;
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
	
	public List<TrainingDTO> searchTrainings(TrainingSx sx) 
	{
		final String BASE_URL = IApiClient.SERVER + "/api/training/search";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(sx, headers);

		ParameterizedTypeReference<List<TrainingDTO>> typeRef = new ParameterizedTypeReference<List<TrainingDTO>>() {
		};

		ResponseEntity<List<TrainingDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public List<TrainingDTO> getEmployeeTrainings(String nid) {
		final String BASE_URL = IApiClient.SERVER + "/api/training/getEmployeeTrainings/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<TrainingDTO>> typeRef = new ParameterizedTypeReference<List<TrainingDTO>>() {
		};

		ResponseEntity<List<TrainingDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	
	
	
}
