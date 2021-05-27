package output;



import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.ChartData;

public class TimelinesChart implements  SystemChart{

	@Override
	public Chart generateChart(ChartData chartData) {
				
		double[] xAxisScale = chartData.calculateYear_X_axisScale();
		
		
		//Defining the axes               
		NumberAxis xAxis = new NumberAxis(xAxisScale[0],xAxisScale[1],xAxisScale[2]);
	                	       
		
		NumberAxis yAxis = new NumberAxis();
	      
	      //Creating the TimeLine chart 
		LineChart<Number, Number> lineChart = new LineChart(xAxis, yAxis);
		xAxis.setLabel("Years");
		yAxis.setLabel("Indexes");
		lineChart.setTitle("TimeLine Chart");

		for (String country : chartData.getCountries()) {

			for (int index : chartData.getIndexies()) {
				XYChart.Series serie = new XYChart.Series();
				serie.setName(country + " Index " + chartData.getIndexInfo(index));

				for (int year : chartData.GetYearsAggregated()) {

					Double value = chartData.GetIndexValue(country, year, index);
					if (value != null) {
						serie.getData().add(new XYChart.Data(year, value));
					}

				}
				lineChart.getData().addAll(serie);
			}
		}

	      
	      System.out.print("TimeLine Chart Ready\n");
	      return lineChart;
	}

}
