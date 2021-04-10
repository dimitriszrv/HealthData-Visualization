package model;

import java.util.ArrayList;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartData {
	private String chartTitle;
	private String xAxisLabel;
	private String yAxisLabel;
	// Containing the data for each country
	private ArrayList<XYChart.Series> series;
	
	
	/*
	 * Must return an object that follows this format
	 * final NumberAxis xAxis = new NumberAxis(0, 10, 1);
	 * Where : 
	 * 0 : x Axis starting point
	 * 10: x Axis ending point
	 * 1 : x Axis step size 
	 */
	public NumberAxis calculateXaxisScale() {
		return null;
	}
	
	
	public NumberAxis calculateYaxisScale() {
		return null;
	}
	
}
