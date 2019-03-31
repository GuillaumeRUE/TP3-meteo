package fr.ul.miage.rue.TP3;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsView.fxml"));
		Parent root = (Parent)loader.load();
        stage.setScene(new Scene(root));
        stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
