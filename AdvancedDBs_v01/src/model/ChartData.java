package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartData {
	//per year per-5 year per 10 year 
	private int yearAggregationType;
	
	//ton arithmo tis stilis tou pinaka Data 
	//Gia na kseroume poies metriseis exoume na emfanisoume apo to erwtima
	private int[] indexes ;
	
	//Oi xwres pou exoun epilegei
	private String[] countries;
	
	//to xroniko evros twn etwn pou exoume tis oimerominies
	private int minYear;
	private int maxYear;
	
	//polles xores > polles hmerominies kai gia kathe hmerominia exoume apo mia ews polles metriseis 
	private HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data = new HashMap <String,HashMap<Integer,HashMap<Integer,Double>>>();
	
	private HashMap<Integer,String> indexInfo; 
	
	private void initializeIndexInfo() {
		indexInfo = new HashMap<Integer,String>(); 
		indexInfo.put(1, "Alcohol consumption (per adult)");
		indexInfo.put(2,"Births attended by skilled health staff");
		indexInfo.put(3,"Breast cancer number of female deaths");
		indexInfo.put(4,"Burn deaths (per 100000 people)");
		indexInfo.put(5,"Child mortality 0-5 year olds dying (per 1000 born)");
		indexInfo.put(6,"Children (per woman total fertility)");
		indexInfo.put(7,"Colonandrectum cancer deaths (per 100000 men)");
		indexInfo.put(8,"Food supply (kilocalories per person and day)");
		indexInfo.put(9,"Hapiscore whr");
		indexInfo.put(10,"HDI (human development index)");
		indexInfo.put(11,"HIV deaths in children");
		indexInfo.put(12,"Malaria number of deaths");
		indexInfo.put(13,"Population total");
		indexInfo.put(14,"Total health spending (per person)");
	}  
	

	public ChartData() {
		initializeIndexInfo();
		
	}
	
	public ChartData(HashMap<String, HashMap<Integer, HashMap<Integer, Double>>> data, ArrayList<String> countries, ArrayList<String> years, ArrayList<String> indexes, int yearAggregationType ) {
		initializeIndexInfo();
		this.data = data;
		
		//parse the countries
		int country_size = countries.size();
		this.countries = new String[country_size];
		
		for(int i = 0;i< country_size;i++) {
			this.countries[i] = countries.get(i);  
		}
		
		//Parse the years
		ArrayList<Integer> temp_years = new ArrayList<Integer>();
		for(String y: years) 
			temp_years.add(Integer.parseInt(y));  
		
		this.maxYear = Collections.max(temp_years );
        this.minYear = Collections.min(temp_years );
        
		//Parse the Indexes
		ArrayList<Integer> temp_indexes = new ArrayList<Integer>();
		for(String index: indexes) 
			temp_indexes.add(Integer.parseInt(index));
		
		
		int indexes_size = indexes.size();
		this.indexes =  new int[temp_indexes.size()];
		
		for(int i = 0;i< indexes_size;i++) {
			this.indexes[i] = temp_indexes.get(i);  
			
		}
		
		
		this.yearAggregationType = yearAggregationType;
		System.out.println(yearAggregationType);
		
		System.out.println("Chart Data is Parshed ");
	}
	
	
	public ChartData(HashMap<String, HashMap<Integer, HashMap<Integer, Double>>> data, String[] countries,
			int[] indexes, int minYear, int maxYear, int yearAggregationType) {
		super();
		initializeIndexInfo();
		this.data = data;
		this.countries = countries;
		this.indexes = indexes;
		this.minYear = minYear;
		this.maxYear = maxYear;
		this.yearAggregationType = yearAggregationType;
	}


	public double[] calculateYear_X_axisScale() {
	
		
		return new double[]{this.minYear - 1,this.maxYear+ 1,this.yearAggregationType};
		
		
	}
	
	
	public double[] calculateYaxisScale() {
		double yMax = 10;
		double yMin = 2;
		

		double[] demo = new double[]{yMin -1 ,yMax + 1,1};
		
		return demo;
	}
	
	public HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> GetData(){
		
		return data;
	}
	
	public void setData(HashMap<String,HashMap<Integer,HashMap<Integer,Double>>> data) {
		this.data = data;
	}
	
	public String[] getCountries() {
		String[] countries = new String[this.data.keySet().size()];
		this.data.keySet().toArray(countries);
		return countries;
	}
	
	public int[] getIndexies() {
		return this.indexes;
	}
		
	public void setIndexes(int[] _indexes) {
		this.indexes = _indexes;
	}
	
	private double[] ArraylListMinMax(ArrayList<Double> arr) {
		

  
        /*/ store the length of the ArrayList in variable n
        int n = arr.size();
        System.out.println("ArrayList elements are :");
        
        for (int i = 0; i < n; i++) {
            System.out.print(arr.get(i) + " ");
        }
        */
		
        double max = Collections.max(arr);
        double min = Collections.min(arr);
        System.out.println(" Min: "+min +" Max2: "+ max);
        return new double[] {min,max};
	}
	
	public ArrayList<Integer> GetYearsAggregated() {
		ArrayList<Integer> years = new ArrayList<Integer>();
		for(int y = this.minYear;y<= this.maxYear;y+=this.yearAggregationType) {
			years.add(y);
			
		}
		return years;
		
	}
	
	public Double GetIndexValue(String country, int year, int index) {
		
		if(this.data.containsKey(country)) 
			if(this.data.get(country).containsKey(year))
				if(this.data.get(country).get(year).containsKey(index))
					//return 3.0;
					return this.data.get(country).get(year).get(index);
				else {
					System.out.println("No c no y Index" +country+" "+year+" "+ index);
					return null;}
			else {
				System.out.println("No c no Year" + country+" "+year);
				return null;}
		else {
			System.out.println("No Countrie" + country);
			return null;}
		
	}
	//ScatterPlot Help Methods
	public double[] GetScatterAxisNumber(int index) {
		ArrayList<Double> indexesValues = new ArrayList<Double>();  
		
		for(String country: data.keySet()){
			for(int year : this.data.get(country).keySet()) {
				
				Double indexValue = this.data.get(country).get(year).get(index);
				if (indexValue != null) 
						indexesValues.add(indexValue);
			}
		}
		System.out.println(indexesValues.size());
		return ArraylListMinMax(indexesValues);
	}
	
	public String getIndexInfo(int indexe) {
		return indexInfo.get(indexe);
	}
	
	
}
