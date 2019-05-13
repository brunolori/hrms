package com.bvlsh.hr.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvlsh.hr.assemblers.Assembler;
import com.bvlsh.hr.dto.BankAccountDTO;
import com.bvlsh.hr.forms.BankAccountForm;
import com.bvlsh.hr.forms.BankAccountSx;
import com.bvlsh.hr.services.BankService;
import com.bvlsh.hr.services.TokenService;

@RestController
@RequestMapping("/api/bank")
public class BankAPI {

	
	@Autowired TokenService  tokenService;
	@Autowired BankService bankService;
	
	
	@RequestMapping(value="/registerBankAccount", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerBankAccount(@RequestHeader(value="Authorization") String token, @RequestBody BankAccountForm form)
	{
			String uname = tokenService.getUsername(token);
			
			BankAccountDTO dto = new Assembler().toDto(bankService.registerBankAccount(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/modifyBankAccount", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> modifyBankAccount(@RequestHeader(value="Authorization") String token, @RequestBody BankAccountForm form)
	{
			String uname = tokenService.getUsername(token);
			
			BankAccountDTO dto = new Assembler().toDto(bankService.modifyBankAccount(form, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/deleteBankAccount/{id}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> deleteContact(@RequestHeader(value="Authorization") String token, @PathVariable Integer id)
	{
		String uname = tokenService.getUsername(token);
				
	    bankService.deleteBankAccount(id, uname);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchBankAccounts", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchBankAccounts(@RequestHeader(value="Authorization") String token,@RequestBody BankAccountSx sx)
	{
		String uname = tokenService.getUsername(token);
		List<Integer> deptIds = tokenService.getDeptIds(token);
				
		List<BankAccountDTO> list = new Assembler().bankAccountListToDto(bankService.searchBankAccounts(sx, deptIds, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	

	@RequestMapping(value="/getEmployeeBankAccounts/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployeeBankAccounts(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<BankAccountDTO> list = new Assembler().bankAccountListToDto(bankService.getEmployeeBankAccounts(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
}

