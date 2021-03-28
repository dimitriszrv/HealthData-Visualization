package mainengine;

import output.OutputManager;
import queryprocessor.QueryProcessor;

/**
 * MainEngine instance that connects every package with gui
 */
public class MainEngine implements IMainEngine{
	private static MainEngine mainEngine;
	
	public MainEngine() {}
	
	/** Singleton Pattern.
	 * 	Public static method for getting the MainEngine instance.
	 */
	public static MainEngine getMainEngineInstance() {
		if (mainEngine == null)
			mainEngine = new MainEngine();
		return mainEngine;
	}
 
	@Override
	public void processQuery() {
		QueryProcessor queryprocessor = new QueryProcessor();
		// ... queryprocessor.createQuery();
	}

	@Override
	public void generateBarChart() {
		OutputManager outputmanager = new OutputManager();
		// ... outputmanager.getBarChart();
		
	}

	@Override
	public void generatePieChart() {
		OutputManager outputManager = new OutputManager();
		// ... outputmanager.getPieChart();
		
	}
	
	
}
