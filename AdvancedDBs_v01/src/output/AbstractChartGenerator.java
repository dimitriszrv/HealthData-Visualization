package output;

import javafx.scene.chart.Chart;

public abstract class AbstractChartGenerator {
	
	public AbstractChartGenerator() {}
	
	
	public Chart generateChart() {
		
		return processDataToChart();
	}
	
	public abstract Chart processDataToChart();
	
}
