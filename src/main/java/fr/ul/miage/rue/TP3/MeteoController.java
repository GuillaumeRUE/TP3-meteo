package fr.ul.miage.rue.TP3;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MeteoController implements Initializable {

	@FXML
	Label cityName;
	
	@FXML
	Label temperature;
	
	@FXML
	Label lastReload;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCityName(String text) {
		cityName.setText(text);
	}
	
	public void setTemperature (float temp) {
		temperature.setText(String.valueOf(temp));
	}
	
	public void setLastReload() {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		lastReload.setText(s.format(date.getTime()));
	}
	
	
	
}
