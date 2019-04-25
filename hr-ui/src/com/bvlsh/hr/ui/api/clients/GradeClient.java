package com.bvlsh.hr.ui.api.clients;

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
import com.bvlsh.hr.ui.utils.Util;

@RestController
@RequestMapping("/api/grade")
public class GradeClient {
	
	public EmployeeGradeDTO registerEducationn(GradeForm form)
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

}
