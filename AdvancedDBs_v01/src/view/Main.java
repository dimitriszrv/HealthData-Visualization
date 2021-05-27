package view;

import controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mainengine.MainEngine;

public class Main extends Application {
	
	MainEngine mainengine;
	MainWindowController controller;
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
		controller = new MainWindowController();
		loader.setController(controller);
	
		Parent root = loader.load();
		
		controller.initialize();
		
		Image healthDataImage = new Image("/view/images/medical-report.png");
		
		// Main scene
		Scene sce = new Scene(root, 1260, 600);
		stage.setTitle("Health Data Visualization");
		stage.getIcons().add(healthDataImage);
		stage.setScene(sce);
		stage.setResizable(false);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
