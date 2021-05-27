package output;

import javafx.scene.chart.Chart;
import model.ChartData;

public interface SystemChart {
	
	public Chart generateChart(ChartData _chartData);

}
