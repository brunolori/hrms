package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.bvlsh.hr.ui.dto.DepartmentDTO;
import com.bvlsh.hr.ui.models.TreeModel;
import com.bvlsh.hr.ui.services.DepartmentService;
import com.bvlsh.hr.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpDepartmentBean implements Serializable {
	
	
	
	private TreeNode root;
    
    private TreeNode selectedNode;
     
     
    @PostConstruct
    public void init() {
    	
    	DepartmentDTO rootDept = new DepartmentService().getRootDepartment();
    	
        root = new DefaultTreeNode("root", new TreeModel(rootDept), null);
    }

        
    public void onNodeExpand(NodeExpandEvent event) {
        TreeModel t = (TreeModel)event.getTreeNode().getData();
        if(t.getType() == TreeModel.DEPARTMENT)
        {
        	List<DepartmentDTO> childsDepartments = new DepartmentService().getChildDepartments(t.getDepartmentId());
        	if(childsDepartments == null || childsDepartments.isEmpty() )
        	{
        		Messages.throwFacesMessage("Nuk ka departamente ne varesi", 2);
        		return;
        	}
        	
        	for(DepartmentDTO dpt : childsDepartments)
        	{
        		new DefaultTreeNode("department", dpt, event.getTreeNode());
        	}
        }
    }
 
    public void onNodeCollapse(NodeCollapseEvent event) {
        
    }
 
    public void onNodeSelect(NodeSelectEvent event) {
       
    }
 
    public void onNodeUnselect(NodeUnselectEvent event) {
        
    }
	

}
