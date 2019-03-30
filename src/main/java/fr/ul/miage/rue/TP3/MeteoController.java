package fr.ul.miage.rue.TP3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MeteoController implements Initializable {

	@FXML
	Label cityName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCityName(String text) {
		cityName.setText(text);
	}
	
	
	
}
