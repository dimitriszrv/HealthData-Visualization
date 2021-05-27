package model;

import java.util.ArrayList;

public class Query {
	private ArrayList<String> countries;
	private ArrayList<String> indexes;
	private ArrayList<String> years;
	
	private int yearAggregationType;
	
	public Query(ArrayList<String> countries, ArrayList<String> indexes, ArrayList<String> years,int yearAggregationType) {
		this.countries = countries;
		this.indexes = indexes;
		this.years = years;
		this.yearAggregationType = yearAggregationType;
	}
	
	public ArrayList<String> getCountries() {
		return countries;
	}
	
	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}

	public ArrayList<String> getIndexes() {
		return indexes;
	}

	public void setIndexes(ArrayList<String> indexes) {
		this.indexes = indexes;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}
	
	public int getYearAggregationType() {
		
		return this.yearAggregationType;
	}
	
	

}
