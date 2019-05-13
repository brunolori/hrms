package com.bvlsh.hr.ui.api.clients;

import java.text.SimpleDateFormat;
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
import com.bvlsh.hr.ui.models.PersonRest;
import com.bvlsh.hr.ui.models.PhotoRest;
import com.bvlsh.hr.ui.utils.StringUtil;

public class NcrClient {

	
	public List<PersonRest> searchPerson(String nid,String name, String surname, Date dob) //dd/MM/yyyy
	{
		String BASE_URL = IApiClient.NCR_API + "/rest/ncr/searchPerson";
		
		boolean isFirst = true;
		
		if(StringUtil.isValid(nid))
		{
			BASE_URL += ((isFirst?"?":"&") + ("nid=" + nid.replace(" ", "").toUpperCase()));
			isFirst = false;
		}
		if(StringUtil.isValid(name))
		{
			BASE_URL += ((isFirst?"?":"&") + ("name=" + name.replace(" ", "").toUpperCase()));
			isFirst = false;
		}
		if(StringUtil.isValid(surname))
		{
			BASE_URL += ((isFirst?"?":"&") + ("surname=" + surname.replace(" ", "").toUpperCase()));
			isFirst = false;
		}
		if(dob != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("dob=" + new SimpleDateFormat("dd/MM/yyyy").format(dob)));
			isFirst = false;
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ParameterizedTypeReference<List<PersonRest>> typeRef = new ParameterizedTypeReference<List<PersonRest>>() {
		};

		ResponseEntity<List<PersonRest>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, typeRef);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	public PhotoRest getPersonPhoto(String nid)
	{
		final String BASE_URL = IApiClient.NCR_API + "/rest/ncr/getPersonSinglePhoto?nid="+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);


		ResponseEntity<PhotoRest> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				entity, PhotoRest.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}
	
	
}
