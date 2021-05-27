package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainengine.MainEngine;

public class DatabaseWindowController implements Initializable{
	  
	private String _USERNAME;
	private String _PASSWORD;
	private List<model.Year> years;
	private MainWindowController mainWindowController;
	
	@FXML
	private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    
    @FXML
    private VBox vboxContainer;

    private FXMLLoader loader;
    private Parent root1;
    
	public DatabaseWindowController() { }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void connectDBonAction(ActionEvent event) throws Exception {
		this._USERNAME = usernameField.getText();
		this._PASSWORD= passwordField.getText();
	    	
	    System.out.println("***DATABASE CONNECTION***\nUsername: "+_USERNAME+"\n"+ "Password: " + _PASSWORD+"\n");
	    
	    MainEngine mainengine = MainEngine.getMainEngineInstance();
	    if (mainengine.configureDB(_USERNAME, _PASSWORD) == 1) {
	    	loader = new FXMLLoader(getClass().getResource("/view/AlertWindow.fxml"));
	    	AlertWindowController controller= new AlertWindowController("Connected to Database Successfully",
	    																"Alright",
	    																"\\src\\view\\images\\check.png");
	    	
			loader.setController(controller);
	    	root1 = loader.load();
	        
	        initializeTables();	        
	    }else {
	    	loader = new FXMLLoader(getClass().getResource("/view/AlertWindow.fxml"));
	    	AlertWindowController controller= new AlertWindowController("Cannot connect to Database",
					"Wrong username or password is given",
					"\\src\\view\\images\\warning.png");
	    	loader.setController(controller);
	    	root1 = loader.load();
	    }
	    
		// TODO Close Database Window
		Image healthDataImage = new Image("/view/images/medical-report.png");

		Stage stage = new Stage();
		stage.setTitle("DB Connection Message");
		stage.getIcons().add(healthDataImage);
        Scene scene  = new Scene(root1, 549, 160);
        stage.setScene(scene);
        stage.show();
	    
	}
	
	
	public void setLoader(MainWindowController mainWindowController) {
		this.mainWindowController = mainWindowController;
	}
	
	public void initializeTables() throws IOException, SQLException {
		mainWindowController.yearTableInit();
		mainWindowController.countryTableInit();
	}
	
	
	public String getUsername() {
		return _USERNAME;
	}
	
	public String getPassword() {
		return _PASSWORD;
	}
	
	
	
}
