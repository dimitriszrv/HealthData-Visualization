package model;

import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartInfo {
	private String chartTitle;
	private String xAxisLabel;
	private String yAxisLabel;

	
	/*
	 * Must return an object that follows this format
	 * final NumberAxis xAxis = new NumberAxis(0, 10, 1);
	 * Where : 
	 * 0 : x Axis starting point
	 * 10: x Axis ending point
	 * 1 : x Axis step size 
	 */
	public ArrayList<Double> calculateXaxisScale() {
		return null;
	}
	
	
	public ArrayList<Double> calculateYaxisScale() {
		return null;
	}


	
	public String getChartTitle() {
		return chartTitle;
	}


	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}


	public String getxAxisLabel() {
		return xAxisLabel;
	}


	public void setxAxisLabel(String xAxisLabel) {
		this.xAxisLabel = xAxisLabel;
	}


	public String getyAxisLabel() {
		return yAxisLabel;
	}


	public void setyAxisLabel(String yAxisLabel) {
		this.yAxisLabel = yAxisLabel;
	}
	
	
	
}
