package output;

//import javafx.scene.chart.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.ChartData;

public class MyBarChart implements SystemChart {

	@Override
	public Chart generateChart(ChartData chartData) {
		
		double[] xAxisScale = chartData.calculateYear_X_axisScale();
		
		//Defining the axes               
		CategoryAxis xAxis = new CategoryAxis();
		
		
		xAxis.setLabel("Years");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Values");
		


		BarChart barChart = new BarChart(xAxis, yAxis);
        
		
		
		barChart.setTitle("Bar Chart");

		for (String country : chartData.getCountries()) {

			for (int index : chartData.getIndexies()) {
				XYChart.Series serie = new XYChart.Series();
				serie.setName(country + " Index " + chartData.getIndexInfo(index));

				for (int year : chartData.GetYearsAggregated()) {

					Double value = chartData.GetIndexValue(country, year, index);
					if (value != null) {
						serie.getData().add(new XYChart.Data(String.valueOf(year), value));
					}

				}
				barChart.getData().addAll(serie);
			}
		}
		
        return barChart;
	}

}
