package queryprocessor;


import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

import model.ChartData;
import model.Query;

/**
 * Class responsible for creating the query
 * Connects to Database and get the data
 */
public class QueryProcessor {
	private ChartData chartData;
	private Database database;
	
	/*
	 * Tha prwtina na diname ta stoixeia tis sundesis 
	 * apo ton constructor ris main Engine to sizitame
	 */
	
	public QueryProcessor() {
		database = Database.getDatabaseInstance();
	}

	
	/**
	 * Method that creates the triple Hashmap with the data.
	 * chartData stores this hashmap
	 * @param query Query object containing Arraylists with indexes, countries and years
	 * @throws SQLException
	 */
	public void queryProcess(Query query) throws SQLException {

		// Connect to Database
		database.connect();
		
		// array list to store countries, years and indexes
		ArrayList<String> countries =  query.getCountries();
		ArrayList<String> years = query.getYears();
		ArrayList<String> indexes = query.getIndexes();
		
		int yearAggregationType = query.getYearAggregationType();

		if (yearAggregationType == 1) {
			HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data = aggregationBy1Year(countries,years,indexes,database.getMyConn());
			this.chartData = new ChartData(data,countries,years,indexes,yearAggregationType);
			System.out.println(data);
		}else {
			HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data = aggregationByMoreThan1Year(countries,years,indexes,database.getMyConn(),yearAggregationType);
			this.chartData = new ChartData(data,countries,years,indexes,yearAggregationType);
			System.out.println(data);
		}
			
	}
	
	
	public static HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> aggregationByMoreThan1Year(ArrayList<String> countries,ArrayList<String> years,ArrayList<String> indexes, Connection myConn, int yearAggregationType) throws SQLException {
			
			PreparedStatement myStmt = null;
			ResultSet myQ = null;
			
			HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data = new HashMap <String,HashMap<Integer,HashMap<Integer,Double>>>();
			
			// select table 
			String dataStatement = "select * from Data ";
					
			// statement for countries
			String countryStatement = "where country = ? ";
			// for loop for each country
			for (int c=0; c<countries.size();c++) {
							
				System.out.println("\nCountry: " + countries.get(c) + "\n");
						
				// get each country
				String getCountry = countries.get(c);
									
				// for each year
				for (int y=0;y<years.size();y++){
	
					// statement for years
					String yearsStatement = "and years >= ? and years <= ? ";
					int minYear = Integer.valueOf(years.get(y));
					int maxYear = Integer.valueOf(years.get(y))+(yearAggregationType-1);
												
					// create statement	-- "select * from Data where country = ? and years >= ? and years <= ?";					
					myStmt = myConn.prepareStatement(dataStatement + countryStatement + yearsStatement);
					
					// set country
					myStmt.setString(1,getCountry);
									
					// years >= ?
					myStmt.setDouble(2, minYear);
	
					// years <= ?
					myStmt.setDouble(3, maxYear);
									
					// execute
					myQ = myStmt.executeQuery();
									
								
					// keep a hashmap of indexer and value of it
					HashMap<String, Double> averageValues = new HashMap<String, Double>();
					// keys are indexes and values are the counter for average
					HashMap<String, Integer> counterForAverage = new HashMap<String, Integer>();
					
					while (myQ.next()) {
	
							
						for (int k=0;k<indexes.size();k++) {
				
							// index from database
							String indexQuery = myQ.getString(indexes.get(k));
							
							// if key already in hashmap, calculate average values and put
							if (averageValues.containsKey(indexes.get(k))) {
								
								// check for empty strings
								if (!indexQuery.equals("")) {
									
									// update counter of index
									counterForAverage.put(indexes.get(k),counterForAverage.get(indexes.get(k))+1);
									// calculate average values
									if (averageValues.get(indexes.get(k)) == null) {
										averageValues.put(indexes.get(k), Double.parseDouble(indexQuery));
									}else {
										double calculateAverage = averageValues.get(indexes.get(k))+Double.parseDouble(indexQuery);
										averageValues.put(indexes.get(k), calculateAverage);
									}
								}

							// first time we need to put key and value
							}else {
								// if not empty string
								if (!indexQuery.equals("")) {
									averageValues.put(indexes.get(k), Double.parseDouble(indexQuery));
									counterForAverage.put(indexes.get(k),1);
								}else {
									averageValues.put(indexes.get(k), null);
									counterForAverage.put(indexes.get(k),0);
								}	
							}
						}					
					}
					// now through a for loop of hashmap averageValues - find keys and values
					// and put it into data
					
					// the first time needed to put country and year into hashmap 
					if (!data.containsKey(getCountry)) {
						data.put(getCountry,new HashMap<Integer,HashMap<Integer,Double>>());
						data.get(getCountry).put((minYear),new HashMap<Integer,Double>());	
					}else {
						data.get(getCountry).put((minYear),new HashMap<Integer,Double>());
					}
								
					// for loop to get keys and values
					for (Entry<String, Double> val: averageValues.entrySet()) {
						//System.out.println("Key: "+val.getKey() + " & Value: " + val.getValue());
						if (val.getValue()!=null) {
							int key = Integer.parseInt(val.getKey());
							double value = val.getValue();
							int counter = counterForAverage.get(val.getKey());
							data.get(getCountry).get(minYear).put(key,(value/counter));
						}
					}
				}}
				return(data);
		}
	
	public static HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> aggregationBy1Year(ArrayList<String> countries,ArrayList<String> years,ArrayList<String> indexes, Connection myConn) throws SQLException {
		
		PreparedStatement myStmt = null;
		ResultSet myQ = null;
		
		HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data = new HashMap <String,HashMap<Integer,HashMap<Integer,Double>>>();
		
		// select table 
		String dataStatement = "select * from Data ";
				
		// statement for countries
		String countryStatement = "where country = ? ";
		
	
		// for loop for each country
		for (int c=0; c<countries.size();c++) {
						
			System.out.println("\nCountry: " + countries.get(c) + "\n");
						
			// get each country
			String getCountry = countries.get(c);
	
			// for each year
			for (int y=0;y<years.size();y++){
							
				String yearsStatement = "and years = ? ";
							
				// create statement -- "select * from Data where country = ? and years = ";						
				myStmt = myConn.prepareStatement(dataStatement + countryStatement + yearsStatement);
				
				// set country
				myStmt.setString(1,getCountry);
							
				// set year
				int year1 = Integer.parseInt(years.get(y));
				myStmt.setDouble(2, year1);
							
				// execute
				myQ = myStmt.executeQuery();
								
				// keep a flag to check each time for country and year
				Boolean flagIfYears = false;
				while (myQ.next()) {
	
					// for all indexes
					for (int k=0;k<indexes.size();k++) {
									
						// get country from database 
						String countryQuery =  myQ.getString("country");
						// get year from database
						String yearQuery = myQ.getString("years");
						// get index from database
						String indexQuery = myQ.getString(indexes.get(k));
									
						System.out.println(countryQuery+" "+yearQuery+" "+indexQuery);
									
						if (!indexQuery.equals("")) {
							
							// the first time needed to put country into map
							if (!data.containsKey(countryQuery)) {
								data.put(countryQuery,new HashMap<Integer,HashMap<Integer,Double>>());
							}		
		
							// if there is only one index, add country, year and value of that index
							if (indexes.size() == 1) {
								data.get(countryQuery).put((Integer.parseInt(yearQuery)),new HashMap<Integer,Double>());
								data.get(countryQuery).get(Integer.parseInt(yearQuery)).put(Integer.parseInt(indexes.get(k)),Double.parseDouble(indexQuery));
							}
								
							// else put year and then check for indexes values
							else if (flagIfYears == false) {
								data.get(countryQuery).put((Integer.parseInt(yearQuery)),new HashMap<Integer,Double>());
								data.get(countryQuery).get(Integer.parseInt(yearQuery)).put(Integer.parseInt(indexes.get(k)),Double.parseDouble(indexQuery));
																		
								flagIfYears = true;
											
							// country and year are already 
							}else {					
								data.get(countryQuery).get(Integer.parseInt(yearQuery)).put(Integer.parseInt(indexes.get(k)),Double.parseDouble(indexQuery));
							}
						}
							
					}}	
			}}		
			return(data);
	}
	
	
	public ChartData GetResults() {
		return this.chartData;
	}
	
	
}