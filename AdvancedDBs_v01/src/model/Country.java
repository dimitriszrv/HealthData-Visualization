package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country {
	private StringProperty countryName;
	 
	 public void setCountryName(String value) { 
		 countryNameProperty().set(value); 
	 }
    
	 public String getCountryName() {
		 return countryNameProperty().get();
	 }
	 
    public StringProperty countryNameProperty() {
        if (countryName == null) 
        	countryName= new SimpleStringProperty(this, "countryName");
        return countryName;
    }


    public Country(String countryName) {
        setCountryName(countryName);
    } 
}
