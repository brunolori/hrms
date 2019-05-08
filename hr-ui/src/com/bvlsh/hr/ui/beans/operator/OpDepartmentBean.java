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
import com.bvlsh.hr.ui.dto.DepartmentPositionDTO;
import com.bvlsh.hr.ui.models.TreeModel;
import com.bvlsh.hr.ui.services.DepartmentService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpDepartmentBean implements Serializable {
	
	
	
	TreeNode root;
    TreeNode selectedNode;
    
    
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
        
    public void onNodeExpand(NodeExpandEvent event) {
        event.getTreeNode();
        
    }
 
    public void onNodeCollapse(NodeCollapseEvent event) {
        
    }
 
    public void onNodeSelect(NodeSelectEvent event) {
       
    }
 
    public void onNodeUnselect(NodeUnselectEvent event) {
        
    }
	

}
