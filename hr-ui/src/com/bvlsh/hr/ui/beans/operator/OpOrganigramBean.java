package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;


import com.bvlsh.hr.ui.beans.application.NavBean;
import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.dto.EmployeeDTO;
import com.bvlsh.hr.ui.forms.DepartmentForm;
import com.bvlsh.hr.ui.forms.DepartmentPositionForm;
import com.bvlsh.hr.ui.models.OrganigramModel;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpOrganigramBean implements Serializable {

	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	
	
	
	private OrganigramNode rootNode;
    private OrganigramNode selection;
 
    private boolean zoom = false;
    private int leafNodeConnectorHeight = 0;
    private boolean autoScrollToSelection = false;
 
    
    DepartmentForm departmentForm;
    
    
    List<EmployeeDTO> employees;
    EmployeeDTO selectedEmployee;
    DepartmentPositionForm deptPositionForm;    
    
    List<DepartmentPositionDTO> departmentPositions;
    
    
    
    @PostConstruct
    public void init() {
    	     	
    	this.departmentForm = new DepartmentForm();
    	this.deptPositionForm = new DepartmentPositionForm();
    	DepartmentDTO root = new DepartmentService().getRootDepartment();
    	DepartmentPositionDTO dp = new DepartmentService().getDepartmentSinglePosition(root.getId());
    	OrganigramModel o = (dp == null)? new OrganigramModel(root) : new OrganigramModel(dp);
        rootNode = new DefaultOrganigramNode("root", o, null);
        rootNode.setCollapsible(false);
        rootNode.setDroppable(false);
 
        List<DepartmentDTO> childs = new DepartmentService().getChildDepartments(root.getId());
        if(childs != null && !childs.isEmpty())
        {
	        for(DepartmentDTO d : childs)
	        {
	        	addChilds(rootNode, d);
	        }
        } 
        

    }
    
    
    public void addChilds(OrganigramNode parent, DepartmentDTO data)
    {
    	DepartmentPositionDTO dp = null;
    	if(data.getPositionsNo() == 1)
    	{
        	dp = new DepartmentService().getDepartmentSinglePosition(data.getId());
    	}
    	OrganigramModel o = (dp == null)? new OrganigramModel(data) : new OrganigramModel(dp);
    	
    	OrganigramNode divisionNode = new DefaultOrganigramNode("division", o, parent);
    	divisionNode.setSelectable(true);
    	divisionNode.setExpanded(o.isExpanded());
    	List<DepartmentDTO> childs = new DepartmentService().getChildDepartments(data.getId());
    	if(childs != null && !childs.isEmpty())
    	{
	    	for(DepartmentDTO d : childs)
	    	{
	    		addChilds(divisionNode, d);
	    	}
    	}
    }
    
    public void prepareAddDepartment()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramModel o = (OrganigramModel)currentSelection.getData();
        
        this.departmentForm = new DepartmentForm();
        this.departmentForm.setParentId(o.getDepartmentId());
        
    }
    
    public void addDepartment()
    {
    	try {
    		
	    	DepartmentDTO d = new DepartmentService().registerDepartment(this.departmentForm);
	    	
	    	OrganigramModel o = new OrganigramModel(d);
	    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	    	new DefaultOrganigramNode("division", o, currentSelection);
    	    Messages.throwFacesMessage("Departamenti u shtua me sukses", 1);
    	    
    	}catch(Exception e)
    	{
    		Messages.throwFacesMessage(e);
    	}
    }
    
    
    public void prepareAddPosition()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramModel o = (OrganigramModel)currentSelection.getData();
        
        this.deptPositionForm = new DepartmentPositionForm();
        this.deptPositionForm.setDepartmentId(o.getDepartmentId());
        
    }
    
    public void addPosition()
    {
    	try {
    		    		
	    	DepartmentPositionDTO d = new DepartmentService().registerDepartmentPosition(this.deptPositionForm);
	    	if(d.getDepartment().getPositionsNo() == 1) {
		    	OrganigramModel o = new OrganigramModel(d);
		    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
		    	currentSelection.setData(o);
	    	}
	    	
    	    Messages.throwFacesMessage("Personi u shtua me sukses", 1);
    	    
    	}catch(Exception e)
    	{
    		Messages.throwFacesMessage(e);
    	}
    }
    
    public void prepareAddEmployee()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramModel o = (OrganigramModel)currentSelection.getData();
        o.display();
        
    }
    
    public void prepareRemoveEmployee()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        OrganigramModel o = (OrganigramModel)currentSelection.getData();
        o.display();
        
    }
    
    
    public void listDepartmentPositions()
    {
    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
    	
    	OrganigramModel data = (OrganigramModel)currentSelection.getData();
    	if(data == null)
    	{
    		Messages.throwFacesMessage("Nuk ka pozicione", 2);
    		return;
    	}
    		    	
    	this.departmentPositions = new DepartmentService().getDepartmentPositions(data.getDepartmentId());
    }
    
    
    public void expandDepartmentPositions()
    {    		
	    	
	    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	    	
	    	OrganigramModel data = (OrganigramModel)currentSelection.getData();
	    	if(data == null)
	    	{
	    		Messages.throwFacesMessage("Nuk ka pozicione", 2);
	    		return;
	    	}
	    		    	
	    	List<DepartmentPositionDTO> posList = new DepartmentService().getDepartmentPositions(data.getDepartmentId());
	    	
	    	if(posList == null || posList.isEmpty())
	    	{
	    		Messages.throwFacesMessage("Nuk ka pozicione", 2);
	    		return;
	    	}
	    	
	    	for(DepartmentPositionDTO p : posList)
	    	{
	    		new DefaultOrganigramNode("employee", new OrganigramModel(p), currentSelection);
	    	}
	    	
    }
    
    public void collapseDepartmentPositions()
    {    		
	    	
	    	OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
	    	currentSelection.setChildren(null);
	    	
    }
    
    
    
    public void removeDivision() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getData();
    }
 
    public void removePosition() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getParent().getChildren().remove(currentSelection);
    }
 
 
    
    
	
	
	
	
}
