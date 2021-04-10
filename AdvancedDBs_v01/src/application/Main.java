package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import mainengine.MainEngine;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	MainEngine mainengine;
	
	@Override
	public void start(Stage stage) {
		mainengine = MainEngine.getMainEngineInstance();
		
		//...
		Chart sc = mainengine.generateChart("ScatterPlot");
		
		stage.setTitle("Scatter Chart Sample");
        Scene scene  = new Scene(sc, 500, 400);
        stage.setScene(scene);
        stage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
