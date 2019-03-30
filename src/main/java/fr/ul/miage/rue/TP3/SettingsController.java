package fr.ul.miage.rue.TP3;

import java.io.IOException;

import javafx.application.Platform;
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
	boolean alreadyOpen = false;
	Stage settingsStage;
	MeteoController meteo;
	
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
		if(alreadyOpen) {
			updateMeteoView(event);
		} else {
			alreadyOpen = true;
			openMeteoView(event);
		}
        
		
	}
	
	public void openMeteoView(ActionEvent event) {
		
		settingsStage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MeteoView.fxml"));
			Parent root = (Parent)loader.load();
			meteo = loader.getController();
			setMeteoAttributes();
			meteo.setSettingsController(settingsStage);
			Stage meteoStage = new Stage();
			meteoStage.setScene(new Scene(root));	
			meteoStage.show();
			
			meteoStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			showSettings(false);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMeteoView(ActionEvent event) {
		setMeteoAttributes();
		showSettings(false);
	}
	
	public void setMeteoAttributes() {
		meteo.setCityName(city);
		meteo.setRefresh(reload);
		meteo.SetDatas();
	}
	
	public void setAlreadyOpen(boolean res) {
		alreadyOpen = res;
	}
	
	public void showSettings(boolean res) {
		if(!res) {
			settingsStage.hide();
		} else {
			settingsStage.show();
		}
	}

}
