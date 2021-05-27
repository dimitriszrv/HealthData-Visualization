package queryprocessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Country;
import model.Year;

public class Database {
	private static Database database;
	private Connection myConn;

	private String USERNAME;
	private String PASSWORD; 
	private String DATABASE = "jdbc:mysql://localhost:3306/AdvancedDBs_v01";
	

	
	public Database() {}
	
	public Database(String username, String password) {
		USERNAME = username;
		PASSWORD = password;
	}
	
	/** Singleton Pattern.
	 * 	Public static method for getting the Database instance.
	 */
	public static Database getDatabaseInstance() {
		if (database == null)
			database = new Database();
		return database;
	}
	
	public int connect() {
		try {
			myConn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
			System.out.println("Connected to Database succesfully");
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Connection getMyConn() {
		return myConn;
	}

	public void setMyConn(Connection myConn) {
		this.myConn = myConn;
	}

	
	
	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	

	public List<model.Year> getDByears() throws SQLException{
		ArrayList<String> years = new ArrayList<>();
		
		database = Database.getDatabaseInstance();
		database.connect();
		
		PreparedStatement statement = database.getMyConn().prepareStatement("select distinct years from Data");
		ResultSet myQ = statement.executeQuery();
		
		while (myQ.next()) {
			years.add(myQ.getString("years"));
		}
		
		List<model.Year> yr = new ArrayList<>();
		for(String year : years) {
			Year y = new Year(year);
			yr.add(y);
		}
		
		return yr;
	}
	
	
	public List<model.Country> getDBcountries() throws SQLException{
		ArrayList<String> countries = new ArrayList<>();
		
		database = Database.getDatabaseInstance();
		database.connect();
		
		PreparedStatement statement = database.getMyConn().prepareStatement("select distinct country from Data;");
		ResultSet myQ = statement.executeQuery();
		
		while (myQ.next()) {
			countries.add(myQ.getString("country"));
		}
		
		List<model.Country> listCntry = new ArrayList<>();
		for(String cntry : countries) {
			Country c = new Country(cntry);
			listCntry.add(c);
		}
		
		return listCntry;
		
		
	}
	
}
