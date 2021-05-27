package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Year {
	private StringProperty year;
	 
	 public void setYear(String value) { 
		 yearProperty().set(value); 
	 }
   
	 public String getYear() {
		 return yearProperty().get();
	 }
	 
   public StringProperty yearProperty() {
       if (year == null) 
       	year = new SimpleStringProperty(this, "year");
       return year;
   }


   public Year(String year) {
       setYear(year);
   }
}
