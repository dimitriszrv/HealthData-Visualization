package output;

import java.util.HashMap;

import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import model.ChartData;

public class ScatterPlot implements SystemChart{

	@Override
	public Chart generateChart(ChartData chartData) {
		
		
		String[] countries = chartData.getCountries();
		int [] indexes = chartData.getIndexies();
		
		double[] xAxisScale = chartData.GetScatterAxisNumber(indexes[0]);
		double[] yAxisScale = chartData.GetScatterAxisNumber(indexes[1]);
		

		
		
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
	      
	      //Creating the Scatter chart 
	      ScatterChart<Number, Number> scatterChart = new ScatterChart(xAxis, yAxis);   
			xAxis.setLabel( chartData.getIndexInfo(indexes[0]) );
			yAxis.setLabel( chartData.getIndexInfo(indexes[1] ));
			scatterChart.setTitle("Scatter Chart");

			

			for (String country : chartData.getCountries())  {
					XYChart.Series serie = new XYChart.Series();
					serie.setName(country);

					for (int year : chartData.GetYearsAggregated()) {

						
						Double valueOne = chartData.GetIndexValue(country, year, indexes[0]);
						Double valueTwo = chartData.GetIndexValue(country, year, indexes[1]);
						if ((valueOne != null) && (valueTwo != null)) {
							System.out.println(" V1: "+valueOne +" V2: "+ valueTwo);
							serie.getData().add(new XYChart.Data(valueOne, valueTwo));
						}
						
					}
					scatterChart.getData().addAll(serie);
				}
			
	      
	      System.out.print("Scatter Chart Ready\n");
	      return scatterChart;
	}
	
}
