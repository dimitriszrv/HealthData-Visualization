package mainengine;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.chart.Chart;
import model.ChartData;

public interface IMainEngine {
	
	public ChartData queryProcess(ArrayList<String> countries, ArrayList<String> indexes, ArrayList<String> years,int yearAggregationType) throws SQLException;
	
	public Chart generateChart(String chartType,ChartData chartData);
	
}
