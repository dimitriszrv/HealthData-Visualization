package queryprocessor;

import java.sql.*;
import java.util.*;

/**
 * Class responsible for creating the query
 * Connects to Database and get the data
 */


public class QueryProcessor {

    //public void queryProcess() {
		    // implementing as arraylist or not ?
	// }
	
	public static void main(String[] args) {
            
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myQ = null;

		try {
			// get a connection
			final String username = "root";
			final String password = "root";
			final String database = "jdbc:mysql://localhost:3306/AdvancedDBs_v01";
		
			// array list to store countries
			
			ArrayList<String> ctr = new ArrayList<String>();
			
			ctr.add("Greece");
			ctr.add("Papua New Guinea");
			ctr.add("Argentina");
	
			
			myConn = DriverManager.getConnection(database,username,password);

			// as many countries and years given
			
			// select table 
			String dataStatement = "select * from Data ";
			
			// statement for countries
			String countryStatement = "where country = ? ";
			
			// statement for years
			String yearsStatement = "and years >= ? and years <= ? ";
			
			
			// create statement						
			myStmt = myConn.prepareStatement(dataStatement + countryStatement + yearsStatement);
			//"select * from Data where country = ? and years >= ? and years <= ?";
			

			// for loop for each country
			for (int i=0; i<ctr.size();i++) {
				
				System.out.println("\nCountry: " + ctr.get(i) + "\n");
				
				// get each country
				String eachCtr = ctr.get(i);
				// set country
				myStmt.setString(1,eachCtr);
				// set years
				myStmt.setDouble(2, 2005);
				myStmt.setDouble(3, 2010);
				
				// execute
				myQ = myStmt.executeQuery();
				
				// print country - years - index1
				while (myQ.next()) {
					System.out.println(myQ.getString("country") + "\t--\tYear:\t" + myQ.getString("years") 
									+ "\tIndex1:\t" + myQ.getString("1") +  "\tIndex3:\t" + myQ.getString("3")  
									+ "\tIndex4:\t" + myQ.getString("4") + "\tIndex5:\t" + myQ.getString("5"));					
				}
			}
			
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

		
	}

}
