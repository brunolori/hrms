package com.bvlsh.hr.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import com.bvlsh.hr.ui.models.KeyValue;
import com.bvlsh.hr.ui.services.StatisticService;
import com.bvlsh.hr.ui.utils.DateUtil;

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
    
    LineChartModel empLineModel;
    LineChartModel jobLeavingLineModel;
    LineChartModel provisionLineModel;
    LineChartModel validationLineModel;
    LineChartModel trainingLineModel;
    
    BarChartModel endJobReasonsBarModel;
    
    Date from, to;

    
    
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
    	initDates();
    	onDateChange();
    }
    
    public void initDates()
    {
    	Date cd = Calendar.getInstance().getTime();
    	this.from = DateUtil.firstDateOfMonth(cd);
    	this.to = cd;
    }
    
    public void onDateChange()
    {
    	if(from == null) from = Calendar.getInstance().getTime();
    	if(to == null)   to = Calendar.getInstance().getTime();
    	
    	if(from.after(to)) from = to;
    	
    	generateEmploymentsChart();
    	generateJobEndingReasonsChart();
    	generateJobLeavingsChart();
    	generateProvisionsChart();
    	generateTrainingsChart();
    	generateValidationsChart();
    	
    }
    
    
    
    
    
    public void generatePayCategoryChart()
    {
    	this.payCategoryPieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        bgColors.add("#03dac6");
        bgColors.add("#6200ee");
        bgColors.add("#018786");
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);
        
        List<KeyValue> list = new StatisticService().employeesByPaymentCategory();
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
        
        PieChartOptions options = new PieChartOptions();
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("left");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(11);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        payCategoryPieModel.setOptions(options);
         
        payCategoryPieModel.setData(data);
    }
    
    public void generateEduChart()
    {
    	
    	this.eduPieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColor = new ArrayList<>();
        bgColor.add("#ffde03");
        bgColor.add("#424250");
        bgColor.add("#3f51b5");
        bgColor.add("#F4511E");
        bgColor.add("#FF9100");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        dataSet.setBackgroundColor(bgColor);
        
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
        
        PieChartOptions options = new PieChartOptions();
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("left");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(11);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        eduPieModel.setOptions(options);
         
        eduPieModel.setData(data);
    }
    
    public void generateGradeChart()
    {
    	this.gradePieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        bgColors.add("#2f8ee5");
        bgColors.add("#6c76af");
        bgColors.add("#AFB42B");
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
         
        PieChartOptions options = new PieChartOptions();
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("left");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(11);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        gradePieModel.setOptions(options);
        
        gradePieModel.setData(data);
    }
    
    public void generateLanguageChart()
    {
    	this.languagePieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        bgColors.add("#63a437");
        bgColors.add("#ffc000");
        bgColors.add("#9ebee5");
        bgColors.add("#2d3e50");
        bgColors.add("#e84c3d");
        bgColors.add("#ed7d31");
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
        
        PieChartOptions options = new PieChartOptions();
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("left");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(11);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        languagePieModel.setOptions(options);
         
        languagePieModel.setData(data);
    }
    
    
    public void generateEmploymentsChart()
    {
    	empLineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setFill(false);
        dataSet.setLabel("Emërimet");
        dataSet.setBorderColor("#f6b69b");
        dataSet.setLineTension(0.1);
        List<KeyValue> list = new StatisticService().employmentsByPeriod(from, to);
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
        	dataSet.setData(Arrays.asList(0));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        data.addChartDataSet(dataSet);
        
        //Options
        LineChartOptions options = new LineChartOptions();        
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Emërimet e reja");
        options.setTitle(title);
         
        empLineModel.setOptions(options);
        empLineModel.setData(data);
    }
    
    public void generateJobEndingReasonsChart()
    {
    	this.endJobReasonsBarModel = new BarChartModel();
        ChartData data = new ChartData();
         
        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setLabel("Arsyet e lirimit");
         
        List<KeyValue> list = new StatisticService().employeesByJobEndingReason(from, to);
        if(list != null && !list.isEmpty())
        {
        	List<Number> values = new ArrayList<>();
        	List<String> labels = new ArrayList<>();
        	for(KeyValue kv : list)
        	{
        		System.out.println(kv.getKey()+" "+kv.getValue());
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
        
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        dataSet.setBackgroundColor(bgColor);
         
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        dataSet.setBorderColor(borderColor);
        dataSet.setBorderWidth(1);
         
        data.addChartDataSet(dataSet);
         
        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
         
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Arsyet e Lirimit");
        options.setTitle(title);
 
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
 
        endJobReasonsBarModel.setOptions(options);
    }
    
    public void generateProvisionsChart()
    {
    	
    	provisionLineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setFill(false);
        dataSet.setLabel("Masat Administrative");
        dataSet.setBorderColor("#c95864");
        dataSet.setLineTension(0.1);
        List<KeyValue> list = new StatisticService().provisionsByPeriod(from, to);
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
        	dataSet.setData(Arrays.asList(0));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        data.addChartDataSet(dataSet);
        provisionLineModel.setData(data);
    	
    }
    
    public void generateTrainingsChart()
    {
    	
    	trainingLineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setFill(false);
        dataSet.setLabel("Trajnimet");
        dataSet.setBorderColor("#39577b");
        dataSet.setLineTension(0.1);
        List<KeyValue> list = new StatisticService().trainingsByPeriod(from, to);
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
        	dataSet.setData(Arrays.asList(0));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        data.addChartDataSet(dataSet);
        trainingLineModel.setData(data);
    	
    }
    
    public void generateValidationsChart()
    {
    	validationLineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setFill(false);
        dataSet.setLabel("Vlerësimet");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        List<KeyValue> list = new StatisticService().validationsByPeriod(from, to);
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
        	dataSet.setData(Arrays.asList(0));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        data.addChartDataSet(dataSet);
        validationLineModel.setData(data);
    }
    
    public void generateJobLeavingsChart()
    {
    	jobLeavingLineModel = new LineChartModel();
        ChartData data = new ChartData();
         
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setFill(false);
        dataSet.setLabel("Largimet/Transferimet");
        dataSet.setBorderColor("#ffc000");
        dataSet.setLineTension(0.1);
        List<KeyValue> list = new StatisticService().jobEndingsByPeriod(from, to);
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
        	dataSet.setData(Arrays.asList(0));
        	data.setLabels(Arrays.asList("nuk ka te dhena"));
        }
        data.addChartDataSet(dataSet);

        jobLeavingLineModel.setData(data);
    }

}
