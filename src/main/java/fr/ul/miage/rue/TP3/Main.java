package fr.ul.miage.rue.TP3;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
