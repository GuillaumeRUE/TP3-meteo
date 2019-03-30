package fr.ul.miage.rue.TP3;

import java.io.IOException;

import fr.ul.miage.meteo.json.Example;
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
	
	String city;
	float temperature;
	
	@FXML
	Button validateButton;
	
	@FXML
	TextField cityName;
	
	@FXML
	TextField reloadTime;
	
	@FXML
	public void onClickValideronClickValider(ActionEvent event) {
		city = cityName.getText();
		int reload = 0;
		try {
			reload = Integer.parseInt(reloadTime.getText());
		} catch (NumberFormatException e) {
			System.out.println("Veuillez entrer un temps de rafraichissement valide");
		}
		getDatas();
        openMeteoView(event);
		
	}
	
	public void getDatas() {
		MeteoClient cl = new MeteoClient();
		Example res = cl.getWeatherByCityName();
        if(res != null) {
        	temperature = res.getMain().getTemp()-273.15f;
        }
	}
	
	public void openMeteoView(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MeteoView.fxml"));
			Parent root = (Parent)loader.load();
			MeteoController meteo = loader.getController();
			meteo.setCityName(city);
			meteo.setTemperature(temperature);
			meteo.setLastReload();
			Stage meteoStage = new Stage();
			meteoStage.setScene(new Scene(root));
			meteoStage.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
