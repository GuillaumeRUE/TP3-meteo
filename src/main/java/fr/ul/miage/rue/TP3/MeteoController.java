package fr.ul.miage.rue.TP3;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import fr.ul.miage.meteo.json.Example;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MeteoController implements Initializable {

	int refresh;
	Stage settings;
	Calendar calendar;
	SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat day = new SimpleDateFormat("EEEEE dd MMMM");
	Timer timer;
	
	@FXML
	Label cityName;
	
	@FXML
	Label temperature;
	
	@FXML
	Label lastReload;
	
	@FXML
	ImageView image;

	@FXML
	Label date;
	
	@FXML
	Button settingsButton;
	
	@FXML
	Pane meteoPane;
	
	@FXML
	public void onClickSettings(ActionEvent event) throws IOException {
		settings.show();
		timer.cancel();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void SetDatas() {
		timer = new Timer();
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
        	temperature.setText(String.format("%.1f", res.getMain().getTemp()-273.15f)+"°C");
        	image.setImage(new Image("https://openweathermap.org/img/w/"+ res.getWeather().get(0).getIcon() +".png"));
        	setLastReload();
        	setDate();
        }
	}
	
	public void setCityName(String text) {
		cityName.setText(text);
	}
	
	public void setLastReload() {
		calendar = Calendar.getInstance();
		lastReload.setText(hour.format(calendar.getTime()));
	}
	
	public void setRefresh(int number) {
		this.refresh = number;
	}
	
	public void setSettingsController(Stage settings) {
		this.settings = settings;
	}
	
	public void setDate() {
		calendar = Calendar.getInstance();
		date.setText(day.format(calendar.getTime()));
	}
	
}
