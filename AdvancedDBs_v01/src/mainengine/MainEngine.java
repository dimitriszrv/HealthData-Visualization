package mainengine;

import java.util.ArrayList;

import javafx.scene.chart.Chart;
import output.AbstractChartGenerator;
import output.GeneratorFactory;
import queryprocessor.QueryProcessor;

/**
 * MainEngine instance that connects every package with gui
 */
public class MainEngine implements IMainEngine{
	private static MainEngine mainEngine;
	private static GeneratorFactory generatorFactory;
	
	public MainEngine() {
		generatorFactory = new GeneratorFactory();
	}
	
	/** Singleton Pattern.
	 * 	Public static method for getting the MainEngine instance.
	 */
	public static MainEngine getMainEngineInstance() {
		if (mainEngine == null)
			mainEngine = new MainEngine();
		return mainEngine;
	}
 
	@Override
	public void queryProcess(ArrayList<String> countries, ArrayList<String> indexes, ArrayList<String> years) {
		QueryProcessor queryprocessor = new QueryProcessor();
		// ... queryprocessor.createQuery();
	}

	@Override
	public Chart generateChart(String chartType) {
		AbstractChartGenerator generator = generatorFactory.createGenerator(chartType);
		
		return generator.generateChart();
	}

	
	
}
