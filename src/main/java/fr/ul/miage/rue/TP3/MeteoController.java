package fr.ul.miage.rue.TP3;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import fr.ul.miage.meteo.json.Example;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MeteoController implements Initializable {

	int refresh;
	
	@FXML
	Label cityName;
	
	@FXML
	Label temperature;
	
	@FXML
	Label lastReload;
	
	@FXML
	ImageView image;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void SetDatas() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Platform.runLater(() -> getDatas());
			}
			
		},0,refresh);
	}
	
	public void getDatas() {
		MeteoClient cl = new MeteoClient();
		Example res = cl.getWeatherByCityName();
        if(res != null) {
        	temperature.setText(String.valueOf(res.getMain().getTemp()-273.15f));
        	image.setImage(new Image("https://openweathermap.org/img/w/"+ res.getWeather().get(0).getIcon() +".png"));
        	setLastReload();
        }
	}
	
	public void setCityName(String text) {
		cityName.setText(text);
	}
	
	public void setLastReload() {
		Calendar date = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		lastReload.setText(s.format(date.getTime()));
	}
	
	public void setRefresh(int number) {
		this.refresh = number;
	}
	
}
