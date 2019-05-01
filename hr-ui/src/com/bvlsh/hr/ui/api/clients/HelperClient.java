package com.bvlsh.hr.ui.api.clients;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bvlsh.hr.ui.api.security.ApiErrorHandler;
import com.bvlsh.hr.ui.constants.IApiClient;
import com.bvlsh.hr.ui.dto.BankDTO;
import com.bvlsh.hr.ui.dto.ContactTypeDTO;
import com.bvlsh.hr.ui.dto.DepartmentCategoryDTO;
import com.bvlsh.hr.ui.dto.EducationTypeDTO;
import com.bvlsh.hr.ui.dto.ForeignLanguageDTO;
import com.bvlsh.hr.ui.dto.GradeDTO;
import com.bvlsh.hr.ui.dto.InstitutionDTO;
import com.bvlsh.hr.ui.dto.PaymentCategoryDTO;
import com.bvlsh.hr.ui.dto.PositionDTO;
import com.bvlsh.hr.ui.dto.ProvisionTypeDTO;
import com.bvlsh.hr.ui.dto.RoleDTO;
import com.bvlsh.hr.ui.dto.StateDTO;
import com.bvlsh.hr.ui.dto.StudyFieldDTO;
import com.bvlsh.hr.ui.dto.TrainingTypeDTO;
import com.bvlsh.hr.ui.dto.ValidationTypeDTO;

public class HelperClient {
	
	public List<ProvisionTypeDTO> loadProvisionTypes()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/provisionTypes";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<ProvisionTypeDTO>> typeRef = new ParameterizedTypeReference<List<ProvisionTypeDTO>>() {};
		
		ResponseEntity<List<ProvisionTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<TrainingTypeDTO> loadTrainingTypes()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/trainingTypes";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<TrainingTypeDTO>> typeRef = new ParameterizedTypeReference<List<TrainingTypeDTO>>() {};
		
		ResponseEntity<List<TrainingTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<ValidationTypeDTO> loadValidationTypes()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/validationTypes";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<ValidationTypeDTO>> typeRef = new ParameterizedTypeReference<List<ValidationTypeDTO>>() {};
		
		ResponseEntity<List<ValidationTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public List<ForeignLanguageDTO> loadForeignLanguages()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/foreignLanguages";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<ForeignLanguageDTO>> typeRef = new ParameterizedTypeReference<List<ForeignLanguageDTO>>() {};
		
		ResponseEntity<List<ForeignLanguageDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public List<StudyFieldDTO> loadStudyFields()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/studyFields";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<StudyFieldDTO>> typeRef = new ParameterizedTypeReference<List<StudyFieldDTO>>() {};
		
		ResponseEntity<List<StudyFieldDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<EducationTypeDTO> loadEducationTypes()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/educationTypes";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<EducationTypeDTO>> typeRef = new ParameterizedTypeReference<List<EducationTypeDTO>>() {};
		
		ResponseEntity<List<EducationTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<InstitutionDTO> loadInstitutions()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/institutions";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<InstitutionDTO>> typeRef = new ParameterizedTypeReference<List<InstitutionDTO>>() {};
		
		ResponseEntity<List<InstitutionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	public List<ContactTypeDTO> loadContactTypes()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/contactTypes";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<ContactTypeDTO>> typeRef = new ParameterizedTypeReference<List<ContactTypeDTO>>() {};
		
		ResponseEntity<List<ContactTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	public List<PositionDTO> loadPositions()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/positions";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PositionDTO>> typeRef = new ParameterizedTypeReference<List<PositionDTO>>() {};
		
		ResponseEntity<List<PositionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}


	public List<BankDTO> loadBanks()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/banks";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<BankDTO>> typeRef = new ParameterizedTypeReference<List<BankDTO>>() {};
		
		ResponseEntity<List<BankDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PaymentCategoryDTO> loadPaymentCategories()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/paymentCategories";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PaymentCategoryDTO>> typeRef = new ParameterizedTypeReference<List<PaymentCategoryDTO>>() {};
		
		ResponseEntity<List<PaymentCategoryDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public List<DepartmentCategoryDTO> loadDepartmentCategories()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/departmentCategories";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentCategoryDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentCategoryDTO>>() {};
		
		ResponseEntity<List<DepartmentCategoryDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public List<StateDTO> loadStates()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/states";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<StateDTO>> typeRef = new ParameterizedTypeReference<List<StateDTO>>() {};
		
		ResponseEntity<List<StateDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK )
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	public List<RoleDTO> loadRoles()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/roles";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<RoleDTO>> typeRef = new ParameterizedTypeReference<List<RoleDTO>>() {};
		
		ResponseEntity<List<RoleDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<GradeDTO> loadGrades() {
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/grades";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<GradeDTO>> typeRef = new ParameterizedTypeReference<List<GradeDTO>>() {};
		
		ResponseEntity<List<GradeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}


}
