package mainengine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.chart.Chart;
import model.ChartData;
import model.Query;
import output.ChartFactory;
import output.SystemChart;
import queryprocessor.Database;
import queryprocessor.QueryProcessor;

/**
 * MainEngine instance that connects every package with gui
 */
public class MainEngine implements IMainEngine{
	private static MainEngine mainEngine;
	
	private QueryProcessor _queryProcessor;
	private ChartFactory _chartFactory;
	
	
	private SystemChart _chart;
	
	
	
	
	public MainEngine() {
		
		_queryProcessor = new QueryProcessor();
		_chartFactory = new ChartFactory(); 
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
	public ChartData queryProcess(ArrayList<String> countries, ArrayList<String> indexes, ArrayList<String> years,int yearAggregationType) {
		
		Query q = new Query(countries, indexes, years,yearAggregationType);
		
		try {
			_queryProcessor.queryProcess(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return _queryProcessor.GetResults();
		
		//return setUpChartData();
	}

	@Override
	public Chart generateChart(String chartType,ChartData chartData) {
		
		_chart = _chartFactory.createChart(chartType);
		
		return _chart.generateChart(chartData);
	}
	
	/**
	 * @param username MySQL's username
	 * @param password MySQL's password
	 * @return an int for flag use
	 * @throws Exception
	 */
	public int configureDB(String username, String password) throws Exception {
		Database db = Database.getDatabaseInstance();
		db.setPASSWORD(password);
		db.setUSERNAME(username);
		
		if (db.connect() == 1)
			return 1;
		else
			return 0;
	}
	
	// Get all countries from Database
	public List<model.Country> getDBcountries() throws SQLException{
		return Database.getDatabaseInstance().getDBcountries();
	}
	
	// Get all Years from Database
	public List<model.Year> getDByears() throws SQLException{
		return Database.getDatabaseInstance().getDByears();
	}
	
	private static ChartData setUpChartData() {
		
		//       Xwra        Hmerominia         Index   TImi 
		HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data = new HashMap <String,HashMap<Integer,HashMap<Integer,Double>>>();
		
		HashMap<Integer,HashMap<Integer,Double>> GreeceDates = new HashMap<Integer,HashMap<Integer,Double>>();
		HashMap<Integer,HashMap<Integer,Double>> NewGuinneaDates = new HashMap<Integer,HashMap<Integer,Double>>();
		HashMap<Integer,HashMap<Integer,Double>> ArgentinaDates = new HashMap<Integer,HashMap<Integer,Double>>();
		
		HashMap<Integer,Double> Greece2005indexesXvalues = new HashMap<Integer,Double>();
		Greece2005indexesXvalues.put(1, 10.8);
		Greece2005indexesXvalues.put(3, 2100.8);
		Greece2005indexesXvalues.put(4, 0.7);
		Greece2005indexesXvalues.put(5, 4.56);
		
		GreeceDates.put(2005, Greece2005indexesXvalues);
		
		HashMap<Integer,Double> Greece2006indexesXvalues = new HashMap<Integer,Double>();
		Greece2006indexesXvalues.put(3, 2100.8);
		Greece2006indexesXvalues.put(4, 0.67);
		Greece2006indexesXvalues.put(5, 4.31);
		
		GreeceDates.put(2006, Greece2006indexesXvalues);
		
		HashMap<Integer,Double> Greece2007indexesXvalues = new HashMap<Integer,Double>();
		Greece2005indexesXvalues.put(3, 2090.0);
		Greece2005indexesXvalues.put(4, 1.78);
		Greece2005indexesXvalues.put(5, 4.11);
		
		GreeceDates.put(2007, Greece2007indexesXvalues);
		
		HashMap<Integer,Double> Greece2008indexesXvalues = new HashMap<Integer,Double>();
		Greece2005indexesXvalues.put(3, 2000.8);
		Greece2005indexesXvalues.put(4, 0.67);
		Greece2005indexesXvalues.put(5, 3.56);
		
		GreeceDates.put(2008, Greece2008indexesXvalues);
		
		HashMap<Integer,Double> Greece2009indexesXvalues = new HashMap<Integer,Double>();
		Greece2005indexesXvalues.put(3, 2200.8);
		Greece2005indexesXvalues.put(4, 0.9);
		Greece2005indexesXvalues.put(5, 5.56);
		
		GreeceDates.put(2009, Greece2009indexesXvalues);
		
		HashMap<Integer,Double> Greece2010indexesXvalues = new HashMap<Integer,Double>();
		Greece2005indexesXvalues.put(3, 2100.8);
		Greece2005indexesXvalues.put(4, 0.7);
		Greece2005indexesXvalues.put(5, 4.56);
		
		GreeceDates.put(2010, Greece2010indexesXvalues);
		
		
		//NewGuinnea
		
		

		HashMap<Integer,Double> NewGuinnea2005indexesXvalues = new HashMap<Integer,Double>();
		NewGuinnea2005indexesXvalues.put(1, 10.8);
		NewGuinnea2005indexesXvalues.put(3, 5900.8);
		NewGuinnea2005indexesXvalues.put(4, 2.17);
		NewGuinnea2005indexesXvalues.put(5, 16.56);
		
		NewGuinneaDates.put(2005, NewGuinnea2005indexesXvalues);
		
		HashMap<Integer,Double> NewGuinnea2006indexesXvalues = new HashMap<Integer,Double>();
		NewGuinnea2006indexesXvalues.put(3, 5960.8);
		NewGuinnea2006indexesXvalues.put(4, 2.67);
		NewGuinnea2006indexesXvalues.put(5, 16.31);
		
		NewGuinneaDates.put(2006, NewGuinnea2006indexesXvalues);
		
		HashMap<Integer,Double> NewGuinnea2007indexesXvalues = new HashMap<Integer,Double>();
		NewGuinnea2005indexesXvalues.put(3, 6000.0);
		NewGuinnea2005indexesXvalues.put(4, 2.78);
		NewGuinnea2005indexesXvalues.put(5, 14.11);
		
		NewGuinneaDates.put(2007, NewGuinnea2007indexesXvalues);
		
		HashMap<Integer,Double> NewGuinnea2008indexesXvalues = new HashMap<Integer,Double>();
		NewGuinnea2005indexesXvalues.put(3, 6000.8);
		NewGuinnea2005indexesXvalues.put(4, 2.67);
		NewGuinnea2005indexesXvalues.put(5, 15.56);
		
		NewGuinneaDates.put(2008, NewGuinnea2008indexesXvalues);
		
		HashMap<Integer,Double> NewGuinnea2009indexesXvalues = new HashMap<Integer,Double>();
		NewGuinnea2005indexesXvalues.put(3, 6050.8);
		NewGuinnea2005indexesXvalues.put(4, 1.9);
		NewGuinnea2005indexesXvalues.put(5, 15.56);
		
		NewGuinneaDates.put(2009, NewGuinnea2009indexesXvalues);
		
		HashMap<Integer,Double> NewGuinnea2010indexesXvalues = new HashMap<Integer,Double>();
		NewGuinnea2005indexesXvalues.put(3, 6100.8);
		NewGuinnea2005indexesXvalues.put(4, 2.7);
		NewGuinnea2005indexesXvalues.put(5, 14.56);
		
		NewGuinneaDates.put(2010, NewGuinnea2010indexesXvalues);
		
		data.put("Greece", GreeceDates);
		data.put("Argentina", NewGuinneaDates);
		

		ChartData _chartData =  new ChartData(data,new String[]{"Greece","Argentina"}, new int[]{4,5},2005,2008,1  ) ;

		
		return _chartData;
		
	}

	
	
}
