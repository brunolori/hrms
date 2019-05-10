package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.forms.DepartmentForm;
import com.bvlsh.hr.ui.forms.DepartmentPositionForm;
import com.bvlsh.hr.ui.forms.EmployeeForm;
import com.bvlsh.hr.ui.models.Param;
import com.bvlsh.hr.ui.models.TreeModel;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.services.EmployeeService;
import com.bvlsh.hr.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpDepartmentBean implements Serializable {
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	TreeNode root;
    TreeNode selectedNode;
    
    DepartmentForm departmentForm;
    DepartmentPositionForm positionForm;
    EmployeeForm employeeForm;
    
    DepartmentDTO selectedDepartment;
    List<DepartmentPositionDTO> departmentPositions;
     
     
    @PostConstruct
    public void init() {
    	
    	DepartmentDTO rootDept = new DepartmentService().getRootDepartment();
    	
        this.root = new DefaultTreeNode("root", new TreeModel(rootDept), null);
        TreeNode strRoot = new DefaultTreeNode("root", new TreeModel(rootDept), root);
        List<DepartmentDTO> childs = new DepartmentService().getChildDepartments(rootDept.getId());
        
        if(childs != null && !childs.isEmpty())
        {
	        for(DepartmentDTO d : childs)
	        {
	        	addChilds(strRoot, d);
	        }
        } 
        
        initForm();
    }
    
    public void addChilds(TreeNode parent, DepartmentDTO data)
    {
    	DepartmentPositionDTO dp = null;
    	if(data.getPositionsNo() == 1)
    	{
        	dp = new DepartmentService().getDepartmentSinglePosition(data.getId());
    	}
    	TreeModel o = (dp == null)? new TreeModel(data) : new TreeModel(dp);
    	
    	TreeNode divisionNode = new DefaultTreeNode("department", o, parent);
    	List<DepartmentDTO> childs = new DepartmentService().getChildDepartments(data.getId());
    	if(childs != null && !childs.isEmpty())
    	{
	    	for(DepartmentDTO d : childs)
	    	{
	    		addChilds(divisionNode, d);
	    	}
    	}
    }

    public void initForm()
    {
    	this.departmentForm = new DepartmentForm();
    	this.positionForm = new DepartmentPositionForm();
    	this.employeeForm = new EmployeeForm();
    	this.selectedDepartment = null;
    	this.departmentPositions = null;    	
    }
    
    public void registerDepartment()
    {
    	try {
    		
    		new DepartmentService().registerDepartment(this.departmentForm);
    		init();
    		Messages.throwFacesMessage("Departamenti u shtua me sukses!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
    }
    
    public void modifyDepartment()
    {
    	try {
    		new DepartmentService().modifyDepartment(this.departmentForm);
    		init();
    		Messages.throwFacesMessage("Departamenti u ndryshua me sukses!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
    }
    
    
        
    public void prepareAddDepartmentPosition()
    {
    	this.positionForm = new DepartmentPositionForm();
		this.positionForm.setDepartmentId(this.selectedDepartment.getId());
    }
    
    public void addDepartmentPosition()
    {
    	try {
    		new DepartmentService().registerDepartmentPosition(this.positionForm);
    		this.departmentPositions = new DepartmentService().getDepartmentPositions(this.selectedDepartment.getId());
    		Messages.throwFacesMessage("Pozicioni u shtua me sukses!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
    }
    
    public void prepareModifyDepartmentPosition(DepartmentPositionDTO dp)
    {
    	this.positionForm = new DepartmentPositionForm();
		this.positionForm.setDepartmentId(dp.getDepartment().getId());
		this.positionForm.setId(dp.getId());
		this.positionForm.setName(dp.getName());
		this.positionForm.setPaymentCategoryId(dp.getPaymentCategory().getId());
		this.positionForm.setPositionId(dp.getPosition().getId());
    }
    
    public void modifyDepartmentPosition()
    {
    	try {
    		new DepartmentService().modifyDepartmentPosition(this.positionForm);
    		this.departmentPositions = new DepartmentService().getDepartmentPositions(this.selectedDepartment.getId());
    		Messages.throwFacesMessage("Pozicioni u ndryshua me sukses!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
    }
        
    public void prepareRemoveEmployee(DepartmentPositionDTO dp)
    {
    	if(dp.getCurrentEmployee() == null)
    	{
    		Messages.throwFacesMessage("Pozicioni eshte i lire",2);
    		return;
    	}
    	
    	this.employeeForm = new EmployeeForm();
    	this.employeeForm.setEmploymentId(dp.getCurrentEmployee().getId());
    	    	
    }
    
    public void removeEmployee()
    {
    	try {
	    	new EmployeeService().updateEmployment(this.employeeForm);
	    	this.departmentPositions = new DepartmentService().getDepartmentPositions(this.selectedDepartment.getId());
	    	Messages.throwFacesMessage("Pozicioni u lirua!", 1);
    	}catch(Exception e) {Messages.throwFacesMessage(e);}
    }
    
    
    
 
    public void onNodeSelect(NodeSelectEvent event) {
    	
    	TreeNode node = event.getTreeNode();
    	if(node == null || node.getData() == null) return;
    	
    	TreeModel m = (TreeModel)node.getData();
    	
    	if(m.getType() == TreeModel.DEPARTMENT)
    	{
    		this.selectedDepartment = new DepartmentService().getDepartmentById(m.getDepartmentId());
    		this.departmentForm.setCategoryId(this.selectedDepartment.getCategory().getId());
    		this.departmentForm.setId(this.selectedDepartment.getId());
    		this.departmentForm.setName(this.selectedDepartment.getName());
    		this.departmentForm.setColor(this.selectedDepartment.getColor());
    		this.departmentForm.setExpanded(this.selectedDepartment.isExpanded());
    		if(this.selectedDepartment.getParent() != null)
    		{
    			this.departmentForm.setParentId(this.selectedDepartment.getParent().getId());
    		}
    		
    		this.departmentForm.setPositionsNo(this.selectedDepartment.getPositionsNo());
    		
    		this.departmentPositions = new DepartmentService().getDepartmentPositions(this.selectedDepartment.getId());
    		
    		this.positionForm = new DepartmentPositionForm();
    		this.positionForm.setDepartmentId(this.selectedDepartment.getId());
    	}
       
    }
 
    public void onEmployeeSelect(DepartmentPositionDTO dp) {
    	
    	if(dp.getCurrentEmployee() == null) return;
    	
    	List<Param> params = new ArrayList<>();
		params.add(new Param("nid",dp.getCurrentEmployee().getEmployee().getNid()));
		nav.navigate("employee_view",params);
    }
    
    
    
    public void onNodeExpand(NodeExpandEvent event) {
        event.getTreeNode();
    }
 
    public void onNodeCollapse(NodeCollapseEvent event) {
        
    }
    
    public void onNodeUnselect(NodeUnselectEvent event) {
        
    }
	

}
