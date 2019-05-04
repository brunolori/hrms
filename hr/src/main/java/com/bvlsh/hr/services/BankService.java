package com.bvlsh.hr.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.BankDAO;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.entities.Bank;
import com.bvlsh.hr.entities.BankAccount;
import com.bvlsh.hr.entities.Employee;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.BankAccountForm;
import com.bvlsh.hr.forms.BankAccountSx;
import com.bvlsh.hr.utils.StringUtil;

@Service
public class BankService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired BankDAO bankDAO;
	
	
	public List<BankAccount> searchBankAccounts(BankAccountSx sx, String uname)
	{
		return bankDAO.searchBankAccounts(sx);
	}
	
	
	@Transactional
	public BankAccount registerBankAccount(BankAccountForm form, String uname)
	{
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		if(form.getBankId() == null)
		{
			throw new ValidationException("Zgjidhni banken");
		}
		if(!StringUtil.isValid(form.getIban()))
		{
			throw new ValidationException("Plotesoni nr e llogarise");
		}
		
		BankAccount a = new BankAccount();
		a.setBank(crudDAO.findById(Bank.class, form.getBankId()));
		a.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		a.setIban(form.getIban());
		a.setStatus(IStatus.ACTIVE);
		a.setCreateTime(Calendar.getInstance().getTime());
		a.setCreateUser(uname);
		a.setUpdateTime(Calendar.getInstance().getTime());
		a.setUpdateUser(uname);
		
		return crudDAO.create(a);
		
	}
	
	@Transactional
	public BankAccount modifyBankAccount(BankAccountForm form, String uname)
	{
		if(form.getId() == null)
		{
			throw new ValidationException("Llogaria e papercaktuar");
		}
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Punonjesi i papercaktuar");
		}
		if(form.getBankId() == null)
		{
			throw new ValidationException("Zgjidhni banken");
		}
		if(!StringUtil.isValid(form.getIban()))
		{
			throw new ValidationException("Plotesoni nr e llogarise");
		}
		
		BankAccount a = crudDAO.findById(BankAccount.class, form.getId());
		a.setBank(crudDAO.findById(Bank.class, form.getBankId()));
		a.setEmployee(crudDAO.findById(Employee.class, form.getPersonNid()));
		a.setIban(form.getIban());
		a.setUpdateTime(Calendar.getInstance().getTime());
		a.setUpdateUser(uname);
		
		return crudDAO.update(a);
		
	}
	
	@Transactional
	public void deleteBankAccount(Integer accountId, String uname)
	{		
		BankAccount a = crudDAO.findById(BankAccount.class, accountId);
		a.setStatus(IStatus.NOT_ACTIVE);
		a.setUpdateTime(Calendar.getInstance().getTime());
		a.setUpdateUser(uname);
		
		crudDAO.update(a);
		
	}
	
	public List<BankAccount> getEmployeeBankAccounts(String nid, String uname) {
		return bankDAO.getEmployeeBankAccounts(nid);
	}
	
	
	
	
	

}
