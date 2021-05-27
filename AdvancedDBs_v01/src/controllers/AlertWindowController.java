package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlertWindowController implements Initializable{

    @FXML
    private Button OKbtn;
	
    @FXML
    private Label MessageLabel;

    @FXML
    private Label detailsLabel;
    
    @FXML
    private ImageView img;
    
    private String message;
    private String details;
    private String imageName;
	
    public AlertWindowController(String message, String details, String imageName) {
    	this.message = message;
    	this.details = details;
    	this.imageName = imageName;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MessageLabel.setText(message);
    	detailsLabel.setText(details);
    	
        String workingDirectory = System.getProperty("user.dir");
    	String absolutePath = workingDirectory + '\\'+ imageName;
        
    	File file = new File(absolutePath);
        Image image = new Image(file.toURI().toString());
        img.setImage(image); 
	}
	
    @FXML
    void OkBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) OKbtn.getScene().getWindow();
    	stage.close();
    }
    
    
}
