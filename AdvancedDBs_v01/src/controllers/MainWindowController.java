package controllers;

import java.io.IOException;

/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainengine.MainEngine;
import model.Country;
import model.Index;
import model.Year;
import view.Main;

public class MainWindowController implements Initializable{

	MainEngine mainengine;
	private ArrayList<String> selectedCountries = new ArrayList<>();
	private ArrayList<String> selectedIndexes = new ArrayList<>();
	private ArrayList<String> selectedYears = new ArrayList<>();
	
	private int yearAggregationType = 1;
	
	
	private TableViewSelectionModel<Index> indexSelectionModel;
	private TableViewSelectionModel<Country> countrySelectionModel;
	private TableViewSelectionModel<Year> yearSelectionModel;
	private List<model.Year> years; 
	
	
	public MainWindowController() {
	}
	
	
    @FXML
    private RadioMenuItem fiveYearOption;
    
    @FXML
    private RadioMenuItem tenYearOption;

    @FXML
    private RadioMenuItem twentyYearOption;

    @FXML
    private RadioMenuItem perYearOption;
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Years_Table"
    private TableView<Year> Years_Table; // Value injected by FXMLLoader
    
    @FXML // fx:id="yearCol"
    private TableColumn<?, ?> yearCol; // Value injected by FXMLLoader
    
    @FXML // fx:id="Countries_Table"
    private TableView<Country> Countries_Table; // Value injected by FXMLLoader

    @FXML // fx:id="cntrCol"
    private TableColumn<?, ?> cntrCol; // Value injected by FXMLLoader

    @FXML // fx:id="indexCol"
    private TableColumn<?, ?> indexCol; // Value injected by FXMLLoader
    
    @FXML // fx:id="scatterBtn"
    private Button scatterBtn; // Value injected by FXMLLoader

    @FXML // fx:id="timelinesBtn"
    private Button timelinesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addCountryButton"
    private Button addCountryButton; // Value injected by FXMLLoader

    @FXML // fx:id="barChartBtn"
    private Button barChartBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addIndexButton"
    private Button addIndexButton; // Value injected by FXMLLoader

    @FXML // fx:id="vbox_id"
    private VBox vbox_id; // Value injected by FXMLLoader

    @FXML // fx:id="MenuBar_id"
    private MenuBar MenuBar_id; // Value injected by FXMLLoader

    @FXML // fx:id="HealthIndexes_Table"
    private TableView<Index> HealthIndexes_Table; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
	
    public void initialize() {
    	 assert Countries_Table != null : "fx:id=\"Countries_Table\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert cntrCol != null : "fx:id=\"cntrCol\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert barChartBtn != null : "fx:id=\"barChartBtn\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert addCountryButton != null : "fx:id=\"addCountryButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert Years_Table != null : "fx:id=\"Years_Table\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert addIndexButton != null : "fx:id=\"addIndexButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert vbox_id != null : "fx:id=\"vbox_id\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert scatterBtn != null : "fx:id=\"scatterBtn\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert timelinesBtn != null : "fx:id=\"timelinesBtn\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert yearCol != null : "fx:id=\"yearCol\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert indexCol != null : "fx:id=\"indexCol\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert MenuBar_id != null : "fx:id=\"MenuBar_id\" was not injected: check your FXML file 'MainWindow.fxml'.";
         assert HealthIndexes_Table != null : "fx:id=\"HealthIndexes_Table\" was not injected: check your FXML file 'MainWindow.fxml'.";
		
        indexTableInit(); 
        mainengine = MainEngine.getMainEngineInstance();
    }
    
    private Main main;
    public void setMain(Main main) {
    	this.main = main;
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
    @FXML
    void timelinesOnAction(ActionEvent event) throws Exception {
    	openChartWindow("Timeline");
    }

	
	@FXML
	void handleScatterButtonAction(ActionEvent event) throws Exception {
		openChartWindow("ScatterPlot");
    }
	
	 
    @FXML
    void barChartBtnOnAction(ActionEvent event) throws Exception {
    	openChartWindow("BarChart");
    }
    
    private void openChartWindow(String chartType) throws Exception {
		handleError();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChartWindow.fxml"));
		Parent root = loader.load();
		
						
		// Generate Scatter plot chart. Parametres : countries, indexes that user selected
		Chart sc = mainengine.generateChart(chartType, mainengine.queryProcess(selectedCountries, selectedIndexes, selectedYears, yearAggregationType));
		
		Stage stage = new Stage();
		stage.setTitle(chartType);
        Scene scene  = new Scene(sc, 600, 500);
        stage.setScene(scene);
        stage.show();
    }
    

	@FXML
    void addCountryBtnOnAction(ActionEvent event) {
		String selectedCountry = countrySelectionModel.getSelectedItem().getCountryName();
		
		if (!selectedCountries.contains(selectedCountry)) {
			selectedCountries.add(selectedCountry);
			
			System.out.println("** Countries List UPDATED **\n"+selectedCountries+"\n");
		}else
			System.out.println("### Country ["+selectedCountry+"] already selected!");
    }
	
	@FXML
    void resetCountriesBtnOnAction(ActionEvent event) {
		resetSelectedList("COUNTRIES");
    }
	
    @FXML
    void addIndexBtnOnAction(ActionEvent event) {
    	String selectedIndex = indexSelectionModel.getSelectedItem().getIndexID();
    	
    	if (!selectedIndexes.contains(selectedIndex)) {
    		selectedIndexes.add(selectedIndex);
    		
			System.out.println("** Indexes UPDATED **\n"+selectedIndexes+"\n");
    	}else
			System.out.println("### Index ["+selectedIndex+"] already selected!");

    }
    
    @FXML
    void resetIndexBtnOnAction(ActionEvent event) {
    	resetSelectedList("INDEXES");
    }
    
    
    @FXML
    void addYearBtnOnAction(ActionEvent event) {
    	String selectedYear = yearSelectionModel.getSelectedItem().getYear();
    	
    	if (!selectedYears.contains(selectedYear)) {
    		selectedYears.add(selectedYear);
    		
			System.out.println("** Years UPDATED **\n"+selectedYears+"\n");
    	}else
			System.out.println("### Year ["+selectedYear+"] already selected!");
    }
    
    @FXML
    void resetYearsOnAction(ActionEvent event) {
    	resetSelectedList("YEARS");
    }
    
	
	// Initialize indexes table data
	public void indexTableInit() {

        List<Index> indexesList = List.of(
				new Index("Alcohol consumption (per adult)", "1"),
				new Index("Births attended by skilled health staff", "2"),
				new Index("Breast cancer number of female deaths", "3"),
				new Index("Burn deaths (per 100000 people)", "4"),
				new Index("Child mortality 0-5 year olds dying (per 1000 born)", "5"),
				new Index("Children (per woman total fertility)", "6"),
				new Index("Colonandrectum cancer deaths (per 100000 men)", "7"),
				new Index("Food supply (kilocalories per person and day)", "8"),
				new Index("Hapiscore whr", "9"),
				new Index("HDI (human development index)", "10"),
				new Index("HIV deaths in children", "11"),
				new Index("Malaria number of deaths", "12"),
				new Index("Population total", "13"),
				new Index("Total health spending (per person)", "14"));

		ObservableList<Index> healthIndexes = FXCollections.observableArrayList(indexesList);

		indexCol.setCellValueFactory(
				new PropertyValueFactory<>("indexName"));
        
		HealthIndexes_Table.setItems(healthIndexes);

		indexSelectionModel = HealthIndexes_Table.getSelectionModel();
		indexSelectionModel.setSelectionMode(SelectionMode.SINGLE);
	}
	
	// Initialize countries table data
	public void countryTableInit() throws SQLException {
		List<Country> countries = MainEngine.getMainEngineInstance().getDBcountries();
		
		ObservableList<Country> cntryList = FXCollections.observableArrayList(countries);
		cntrCol.setCellValueFactory(
				new PropertyValueFactory<>("countryName"));
		
		Countries_Table.setItems(cntryList);
	
		countrySelectionModel = Countries_Table.getSelectionModel();
		countrySelectionModel.setSelectionMode(SelectionMode.SINGLE);
	}
	
	
	public void yearTableInit() throws SQLException {
		years = MainEngine.getMainEngineInstance().getDByears();
		
		ObservableList<Year> yrList = FXCollections.observableArrayList(years);
		yearCol.setCellValueFactory(
				new PropertyValueFactory<>("year"));
		
		Years_Table.setItems(yrList);
		
		yearSelectionModel = Years_Table.getSelectionModel();
		yearSelectionModel.setSelectionMode(SelectionMode.SINGLE);
	}
	
	
	// Connect to Database 
	@FXML
	void connectDB_BtnOnAction(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DatabaseWindow.fxml"));
		DatabaseWindowController DBwindowCntrller = new DatabaseWindowController();
		loader.setController(DBwindowCntrller);
		Parent root = loader.load();
		
		// Pass FXML loader to DatabaseWindow
		DBwindowCntrller.setLoader(this);
		
		Image healthDataImage = new Image("/view/images/medical-report.png");
		
		Stage stage = new Stage();
		stage.setTitle("Database Connection");
		stage.getIcons().add(healthDataImage);
		Scene scene  = new Scene(root, 450, 352);
        stage.setScene(scene);
        stage.show();
	}
    
	private void handleError() throws Exception {
		if(selectedCountries.isEmpty() || selectedYears.isEmpty() || selectedIndexes.isEmpty()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlertWindow.fxml"));
	    	AlertWindowController controller= new AlertWindowController("Not enough data",
	    																"Choose one or more from the tables",
	    																"\\src\\view\\images\\warning.png");
	    	
	    	loader.setController(controller);
	    	Parent root1 = loader.load();
	    	
	    	Image healthDataImage = new Image("/view/images/medical-report.png");

			Stage stage = new Stage();
			stage.setTitle("Error Message");
			stage.getIcons().add(healthDataImage);
	        Scene scene  = new Scene(root1, 549, 160);
	        stage.setScene(scene);
	        stage.show();
		}
	}
	

	private void changeYearOption(int option) {
		
		yearAggregationType = option;
		
		try {
			years = MainEngine.getMainEngineInstance().getDByears();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	List<Year> newYearsList = new ArrayList<>();
    	
    	for (int i = 0; i<years.size(); i+=option)
    		newYearsList.add(years.get(i));
    	
    	System.out.println("20 years Option Selected");
    	for(Year yr : newYearsList)
    		System.out.println(yr.getYear());
    	
    	years = newYearsList;
    	
    	ObservableList<Year> yrList = FXCollections.observableArrayList(years);
		yearCol.setCellValueFactory(
				new PropertyValueFactory<>("year"));
		
		Years_Table.setItems(yrList);
		
		yearSelectionModel = Years_Table.getSelectionModel();
		yearSelectionModel.setSelectionMode(SelectionMode.SINGLE);
	}
	
    @FXML
    void twentyYearOptionOnAction(ActionEvent event) {
		changeYearOption(20);
    }
    
    
    @FXML
    void perYearOptionOnAction(ActionEvent event) {
    	changeYearOption(1);
    }

    @FXML
    void fiveYearOptionOnAction(ActionEvent event) {
    	changeYearOption(5);
    }

    @FXML
    void tenYearOptionOnAction(ActionEvent event) {
    	changeYearOption(10);
    }

    private void resetSelectedList(String list) {
    	switch (list) {
		case "YEARS":
	    	selectedYears.removeAll(selectedYears);
		case "INDEXES":
			selectedIndexes.removeAll(selectedIndexes);
		case "COUNTRIES":
			selectedCountries.removeAll(selectedCountries);

		}
    	System.out.println("___ ALL SELECTED "+list+" DELETED ___");
    }
    
    
	
}

