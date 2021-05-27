package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MessageConnectedDBcontroller implements Initializable{
   
	@FXML
    private Button okButton;

    @FXML
    void okBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
