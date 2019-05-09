package com.bvlsh.hr.ui.api.clients;

import java.util.Date;
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
import com.bvlsh.hr.ui.models.KeyValue;
import com.bvlsh.hr.ui.utils.DateUtil;
import com.bvlsh.hr.ui.utils.Util;

public class StatisticClient {
	
	public Long departmentsCount() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/departmentsCount";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		
		ResponseEntity<Long> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, Long.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public Long positionsCount() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/positionsCount";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		
		ResponseEntity<Long> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, Long.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public Long freePositionsCount() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/freePositionsCount";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		
		ResponseEntity<Long> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, Long.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

	public Long employeesCount() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesCount";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		
		ResponseEntity<Long> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, Long.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public Long employeesCountByGender(String gender) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesCountByGender/"+gender;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		
		ResponseEntity<Long> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, Long.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public List<KeyValue> employeesByStudyField() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesByStudyField";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public List<KeyValue> employeesByGrade() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesByGrade";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public List<KeyValue> employeesByForeignLanguage() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesByForeignLanguage";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public List<KeyValue> employeesByPaymentCategory() {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesByPaymentCategory";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public List<KeyValue> employeesByJobEndingReason(Date from, Date to) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employeesByJobEndingReason?from="
								+ DateUtil.formatDateToStringReverse(from)+"&to="+DateUtil.formatDateToStringReverse(to);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public List<KeyValue> employmentsByPeriod(Date from, Date to) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/employmentsByPeriod?from="
								+ DateUtil.formatDateToStringReverse(from)+"&to="+DateUtil.formatDateToStringReverse(to);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public List<KeyValue> jobEndingsByPeriod(Date from, Date to) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/jobEndingsByPeriod?from="
								+ DateUtil.formatDateToStringReverse(from)+"&to="+DateUtil.formatDateToStringReverse(to);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public List<KeyValue> provisionsByPeriod(Date from, Date to) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/provisionsByPeriod?from="
								+ DateUtil.formatDateToStringReverse(from)+"&to="+DateUtil.formatDateToStringReverse(to);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public List<KeyValue> validationsByPeriod(Date from, Date to) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/validationsByPeriod?from="
								+ DateUtil.formatDateToStringReverse(from)+"&to="+DateUtil.formatDateToStringReverse(to);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
	public List<KeyValue> trainingsByPeriod(Date from, Date to) {
		final String BASE_URL = IApiClient.SERVER + "/api/statistic/trainingsByPeriod?from="
								+ DateUtil.formatDateToStringReverse(from)+"&to="+DateUtil.formatDateToStringReverse(to);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {
		};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
}
