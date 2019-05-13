package com.bvlsh.hr.ui.services;

import java.util.List;

import com.bvlsh.hr.ui.api.clients.BankClient;
import com.bvlsh.hr.ui.dto.BankAccountDTO;
import com.bvlsh.hr.ui.forms.BankAccountForm;
import com.bvlsh.hr.ui.forms.BankAccountSx;

public class BankService {

	
	public List<BankAccountDTO> searchBankAccounts(BankAccountSx sx) 
	{
		return new BankClient().searchBankAccounts(sx);
	}
	
	public List<BankAccountDTO> getEmployeeBankAccounts(String nid)
	{
		return new BankClient().getEmployeeBankAccounts(nid);
	}
	
	public BankAccountDTO registerBankAccount(BankAccountForm form)
	{
		return new BankClient().registerBankAccount(form);
	}
	
	public BankAccountDTO modifyBankAccount(BankAccountForm form)
	{
		return new BankClient().modifyBankAccount(form);
	}
	
	public void deleteBankAccount(Integer id) 
	{
		new BankClient().deleteBankAccount(id);
	}
	
	
}
