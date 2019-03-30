package fr.ul.miage.rue.TP3;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import fr.ul.miage.meteo.json.Example;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SettingsController {
	
	String city;
	float temperature;
	String iconePath;
	Image icone;
	int reload;
	
	@FXML
	Button validateButton;
	
	@FXML
	TextField cityName;
	
	@FXML
	TextField reloadTime;
	
	@FXML
	public void onClickValideronClickValider(ActionEvent event) {
		city = cityName.getText();
		try {
			reload = Integer.parseInt(reloadTime.getText())*1000;
		} catch (NumberFormatException e) {
			System.out.println("Veuillez entrer un temps de rafraichissement valide");
		}
        openMeteoView(event);
		
	}
	
	public void openMeteoView(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MeteoView.fxml"));
			Parent root = (Parent)loader.load();
			MeteoController meteo = loader.getController();
			meteo.setCityName(city);
			meteo.setRefresh(reload);
			meteo.SetDatas();
			Stage meteoStage = new Stage();
			meteoStage.setScene(new Scene(root));	
			meteoStage.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
