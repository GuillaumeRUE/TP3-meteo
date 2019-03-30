package fr.ul.miage.rue.TP3;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {
	
	@FXML
	Button validateButton;
	
	@FXML
	TextField cityName;
	
	@FXML
	TextField reloadTime;
	
	@FXML
	public void onClickValideronClickValider(ActionEvent event) {
		String city = cityName.getText();
		int reload = 0;
		try {
			reload = Integer.parseInt(reloadTime.getText());
		} catch (NumberFormatException e) {
			System.out.println("Veuillez entrer un temps de rafraichissement valide");
		}
		System.out.println(city + " " + reload);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MeteoView.fxml"));
			Parent root = (Parent)loader.load();
			
			MeteoController meteo = loader.getController();
			meteo.setCityName(city);
			Stage meteoStage = new Stage();
			meteoStage.setScene(new Scene(root));
			meteoStage.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
