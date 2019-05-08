package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import com.bvlsh.hr.ui.models.KeyValue;
import com.bvlsh.hr.ui.services.StatisticService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OpDashboardBean implements Serializable {
	
	
	
	Long deptCnt;
	Long posCnt;
	Long empCnt;
	Long freePosCnt;
	Long maleEmpCnt;
	Long femaleEmpCnt;
	
    PieChartModel eduPieModel;
    PieChartModel gradePieModel;
    PieChartModel languagePieModel;
    PieChartModel payCategoryPieModel;
    
    
    
    
    @PostConstruct
    public void init() {
    	
    	this.deptCnt = new StatisticService().departmentsCount();
    	this.posCnt = new StatisticService().positionsCount();
    	this.empCnt = new StatisticService().employeesCount();
    	this.freePosCnt = new StatisticService().freePositionsCount();
    	this.maleEmpCnt = new StatisticService().employeesCountByGender("M");
    	this.femaleEmpCnt = new StatisticService().employeesCountByGender("F");
    	generateEduChart();
    	generateGradeChart();
    	generateLanguageChart();
    	generatePayCategoryChart();
    }
    
    public void generatePayCategoryChart()
    {
    	// plotesoje deri te dao
    }
    
    public void generateEduChart()
    {
    	
    	this.eduPieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);
        
        List<KeyValue> list = new StatisticService().employeesByStudyField();
        if(list != null && !list.isEmpty())
        {
        	List<Number> values = new ArrayList<>();
        	List<String> labels = new ArrayList<>();
        	for(KeyValue kv : list)
        	{
        		values.add((Integer)kv.getValue());
        		labels.add(kv.getKey());
        	}
        	dataSet.setData(values);
        	data.setLabels(labels);
        	
        }else
        {
        	dataSet.setData(Arrays.asList(1));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        
        data.addChartDataSet(dataSet);
         
        eduPieModel.setData(data);
    }
    
    public void generateGradeChart()
    {
    	this.gradePieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);
        
        List<KeyValue> list = new StatisticService().employeesByGrade();
        if(list != null && !list.isEmpty())
        {
        	List<Number> values = new ArrayList<>();
        	List<String> labels = new ArrayList<>();
        	for(KeyValue kv : list)
        	{
        		values.add((Integer)kv.getValue());
        		labels.add(kv.getKey());
        	}
        	dataSet.setData(values);
        	data.setLabels(labels);
        	
        }else
        {
        	dataSet.setData(Arrays.asList(1));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        
        data.addChartDataSet(dataSet);
         
        gradePieModel.setData(data);
    }
    
    public void generateLanguageChart()
    {
    	this.languagePieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);
        
        List<KeyValue> list = new StatisticService().employeesByForeignLanguage();
        if(list != null && !list.isEmpty())
        {
        	List<Number> values = new ArrayList<>();
        	List<String> labels = new ArrayList<>();
        	for(KeyValue kv : list)
        	{
        		values.add((Integer)kv.getValue());
        		labels.add(kv.getKey());
        	}
        	dataSet.setData(values);
        	data.setLabels(labels);
        	
        }else
        {
        	dataSet.setData(Arrays.asList(1));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        
        data.addChartDataSet(dataSet);
         
        languagePieModel.setData(data);
    }
    
    
    
    
    

}
