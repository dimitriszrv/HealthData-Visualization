package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Index {
	 private StringProperty indexName;
     private StringProperty indexID;
	 
	 public void setIndexName(String value) { 
    	 indexNameProperty().set(value); 
	 }
     
	 public String getIndexName() {
		 return indexNameProperty().get();
	 }
	 
	 public void setIndexID(String value) {
		 indexIDProperty().set(value);
	 }
	 
	 public String getIndexID() {
		 return indexIDProperty().get();
	 }
	 
     public StringProperty indexNameProperty() {
         if (indexName == null) 
        	 indexName = new SimpleStringProperty(this, "indexName");
         return indexName;
     }

     public StringProperty indexIDProperty() {
         if (indexID == null) 
        	 indexID = new SimpleStringProperty(this, "indexID");
         return indexID;
     }

     public Index(String indexName, String indexID) {
         setIndexName(indexName);
         setIndexID(indexID);
     } 
}
