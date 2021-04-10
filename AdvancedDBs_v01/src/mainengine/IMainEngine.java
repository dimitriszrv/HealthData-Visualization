package mainengine;

import java.util.ArrayList;

import javafx.scene.chart.Chart;

public interface IMainEngine {
	
	public void queryProcess(ArrayList<String> countries, ArrayList<String> indexes, ArrayList<String> years);
	
	public Chart generateChart(String chartType);
	
}
