package com.bvlsh.hr.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvlsh.hr.constants.IStatus;
import com.bvlsh.hr.dao.CrudDAO;
import com.bvlsh.hr.dao.DepartmentDAO;
import com.bvlsh.hr.entities.Department;
import com.bvlsh.hr.entities.DepartmentCategory;
import com.bvlsh.hr.entities.DepartmentPosition;
import com.bvlsh.hr.entities.PaymentCategory;
import com.bvlsh.hr.entities.Position;
import com.bvlsh.hr.entities.User;
import com.bvlsh.hr.exceptions.ValidationException;
import com.bvlsh.hr.forms.DepartmentForm;
import com.bvlsh.hr.forms.DepartmentPositionForm;
import com.bvlsh.hr.utils.StringUtil;



@Service
public class DepartmentService {

	@Autowired CrudDAO crudDAO;
	@Autowired DepartmentDAO departmentDAO;
	
	
	public Department getDepartmentById(Integer id, String uname) {
		return crudDAO.findById(Department.class, id);
	}
	
	public List<Department> listDepartments(String uname, List<Integer> deptIds) {
		return departmentDAO.listDepartments(deptIds);
	}
	
	public Department getRootDepartment(String uname)
	{
		User u = crudDAO.findById(User.class, uname);
		if(u.getRootDepartment() != null) 
		{
			return u.getRootDepartment();
		}
		return departmentDAO.getRootDepartment();
	}
	
	public List<Department> getChildDepartments(Integer deptId)
	{
		return departmentDAO.getChildDepartments(deptId);
	}
	
	public List<Integer> getAllChildDepartmentIds(Integer deptId)
	{
		List<Integer> all = new ArrayList<>();
		all.add(deptId);
		
		List<Department> list = departmentDAO.getChildDepartments(deptId);
		if(list != null && !list.isEmpty())
		{
			for(Department d : list)
			{
				all.addAll(getAllChildDepartmentIds(d.getId()));
			}
			
		}
		
		return all;

	}

	public List<DepartmentPosition> getDepartmentPositions(Integer deptId)
	{
		return departmentDAO.getDepartmentPositions(deptId);
	}
	
	
	@Transactional
	public Department registerDepartment(DepartmentForm form, String uname)
	{
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Ploteso emrin e departamentit");
		}
		
		if(form.getParentId() == null)
		{
			throw new ValidationException("Stuktura prind e papercaktuar");
		}
		
		if(form.getCategoryId() == null)
		{
			throw new ValidationException("Kategoria e papercaktuar");
		}
		
		if(form.getPositionsNo() == null || form.getPositionsNo() <= 0)
		{
			throw new ValidationException("Plotesoni numrin e pozicioneve");
		}
		
		Department d = new Department();
		d.setName(form.getName());
		d.setExpanded(form.isExpanded()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		d.setColor((form.getColor()==null)?null:("#"+form.getColor()));
		d.setCategory(crudDAO.findById(DepartmentCategory.class, form.getCategoryId()));
		d.setPositionsNo(form.getPositionsNo());
		d.setStatus(IStatus.ACTIVE);
		d.setCreateTime(Calendar.getInstance().getTime());
		d.setCreateUser(uname);
		d.setUpdateTime(Calendar.getInstance().getTime());
		d.setUpdateUser(uname);
		Department parent = crudDAO.findById(Department.class, form.getParentId());
		d.setParent(parent);
		
		
		return crudDAO.create(d);
		
	}
	
	@Transactional
	public Department modifyDepartment(DepartmentForm form, String uname) {
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Ploteso emrin e departamentit");
		}
		
		if(form.getParentId() == null)
		{
			throw new ValidationException("Stuktura prind e papercaktuar");
		}
		
		if(form.getCategoryId() == null)
		{
			throw new ValidationException("Kategoria e papercaktuar");
		}
		
		if(form.getPositionsNo() == null || form.getPositionsNo() <= 0)
		{
			form.setPositionsNo(2);
		}
		
		Department d = crudDAO.findById(Department.class, form.getId());
		d.setName(form.getName());
		d.setExpanded(form.isExpanded()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		d.setColor((form.getColor()==null)?null:("#"+form.getColor()));
		d.setCategory(crudDAO.findById(DepartmentCategory.class, form.getCategoryId()));
		d.setPositionsNo(form.getPositionsNo());
		Department parent = crudDAO.findById(Department.class, form.getParentId());
		d.setParent(parent);
		
		d.setUpdateTime(Calendar.getInstance().getTime());
		d.setUpdateUser(uname);
		
		return crudDAO.update(d);
	}
	
	@Transactional
	public DepartmentPosition registerDepartmentPosition(DepartmentPositionForm form, String uname)
	{
		
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Plotesoni emrin e pozicionit");
		}
		if(form.getDepartmentId() == null)
		{
			throw new ValidationException("Zgjidhni departamentin");
		}
		if(form.getPositionId() == null)
		{
			throw new ValidationException("Zgjidhni pozicionin");
		}
		if(form.getPaymentCategoryId() == null)
		{
			throw new ValidationException("Zgjidhni kategorine e pages");
		}
		
		
		Department d = crudDAO.findById(Department.class, form.getDepartmentId());
		
		
		DepartmentPosition dp = new DepartmentPosition();
		dp.setDepartment(d);
		dp.setName(form.getName());
		dp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		dp.setPosition(crudDAO.findById(Position.class, form.getPositionId()));
		dp.setStatus(IStatus.ACTIVE);
		dp.setCreateTime(Calendar.getInstance().getTime());
		dp.setCreateUser(uname);
		dp.setUpdateTime(Calendar.getInstance().getTime());
		dp.setUpdateUser(uname);
		
		return crudDAO.create(dp);
		
	}

	
	
	@Transactional
	public DepartmentPosition modifyDepartmentPosition(DepartmentPositionForm form, String uname) {
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Plotesoni emrin e pozicionit");
		}
		if(form.getDepartmentId() == null)
		{
			throw new ValidationException("Zgjidhni departamentin");
		}
		if(form.getPositionId() == null)
		{
			throw new ValidationException("Zgjidhni pozicionin");
		}
		if(form.getPaymentCategoryId() == null)
		{
			throw new ValidationException("Zgjidhni kategorine e pages");
		}
		
		
		Department d = crudDAO.findById(Department.class, form.getDepartmentId());
		
		
		DepartmentPosition dp = crudDAO.findById(DepartmentPosition.class, form.getId());
		dp.setDepartment(d);
		dp.setName(form.getName());
		dp.setPaymentCategory(crudDAO.findById(PaymentCategory.class, form.getPaymentCategoryId()));
		dp.setPosition(crudDAO.findById(Position.class, form.getPositionId()));
		dp.setUpdateTime(Calendar.getInstance().getTime());
		dp.setUpdateUser(uname);
		
		return crudDAO.update(dp);
	}

	
	@Transactional
	public void deleteDepartmentPosition(Integer id, String uname) {
		
		DepartmentPosition dp = crudDAO.findById(DepartmentPosition.class, id);
		dp.setUpdateTime(Calendar.getInstance().getTime());
		dp.setUpdateUser(uname);
		dp.setStatus(IStatus.NOT_ACTIVE);
		crudDAO.update(dp);
		
	}
	
	
	
	
	
	
}
